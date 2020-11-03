import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

class HttpServer {
    private final File root;

    enum StatusCodes {
        OK(200),
        PartialContent(206),
        MovedPermanently(301),
        MovedTemporarily(302),
        NotModified(304),
        SeeOther(303),
        TemporaryRedirect(307),
        PermanentRedirect(308),
        Forbidden(403),
        NotFound(404),
        MethodNotAllowed(405),
        InternalServerError(500),
        NotImplemented(501),
        BadGateWay(502),
        ServiceUnavailable(503),
        GatewayTimeout(504);
        private int value;
        StatusCodes(int value) {
            this.value = value;
        }
        int getValue() {
            return value;
        }
    }
    private HttpServer(File root, int port) throws IOException {
        this.root = root;

        ServerSocket serverSocket = new ServerSocket(port);
        while (serverSocket.isBound()) {
            try {
                Socket socket = serverSocket.accept();
                process(socket);
                socket.close();
            } catch (IOException ignored) {
                // No operations.
            }
        }
    }

    private void process(Socket socket) throws IOException {
        Request request = readRequest(socket);

        Response response = new Response();
        response.setHeader("Connection", "close");

        try {
            process(request, response);
        } catch (Exception ignored) {
            response.setStatusCode(StatusCodes.BadGateWay.getValue());
        }

        try {
            writeResponse(socket, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeResponse(Socket socket, Response response) throws IOException {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            result.write(("HTTP/1.1 " + response.getStatusCode() + " NA\r\n").getBytes());
            for (Map.Entry<String, String> entry : response.getHeaders().entrySet()) {
                result.write((entry.getKey() + ": " + entry.getValue() + "\r\n").getBytes());
            }
            result.write("\r\n".getBytes());
            if (response.getBody() != null) {
                result.write(response.getBody());
            }

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(result.toByteArray());
            outputStream.close();
        } finally {
            socket.close();
        }
    }

    private void process(Request request, Response response) throws IOException {
        if (!"GET".equals(request.getMethod())) {
            response.setStatusCode(StatusCodes.MethodNotAllowed.getValue());
            return;
        }
        String uri =  URLDecoder.decode(request.getUri(), StandardCharsets.UTF_8.name());
        File file;
        if (!(file = new File(root, uri)).isFile()) {
            file = new File(new File(root, uri), "index.html");
        }
        if (file.isFile()) {
            byte[] body = readFile(file);
            String ETag = Integer.toString(Arrays.hashCode(body));
            String IfNoneMatch = request.getHeader("If-None-Match");
            response.setHeader("ETag", ETag);
            response.setHeader("Content-Length", Integer.toString(body.length));
            response.setHeader("Content-Type", getContentType(file));
            if (IfNoneMatch != null && IfNoneMatch.equals(ETag)) {
                response.setStatusCode(StatusCodes.NotModified.getValue());
            } else {
                response.setStatusCode(StatusCodes.OK.getValue());
                response.setBody(body);
            }
        } else {
            response.setStatusCode(StatusCodes.NotFound.getValue());
        }
    }

    private String getContentType(File file) {
        String path = file.getAbsolutePath();
        if (path.endsWith(".html") || path.endsWith(".htm")) {
            return "text/html";
        }
        if (path.endsWith(".png")) {
            return "image/png";
        }
        throw new IllegalArgumentException();
    }

    private void silentClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception ignored) {
            // No operations.
        }
    }

    private byte[] readInputStream(InputStream inputStream, boolean breakOnCrLf) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        byte[] lastTwoBytes = new byte[2];
        int lastTwoBytesSize = 0;

        try {
            byte[] buffer = new byte[1024];
            while (true) {
                if (breakOnCrLf && lastTwoBytes[0] == 13 && lastTwoBytes[1] == 10) {
                    break;
                }

                int read = inputStream.read(buffer);
                if (breakOnCrLf) {
                    if (read == 1) {
                        if (lastTwoBytesSize > 1) {
                            lastTwoBytes[0] = lastTwoBytes[1];
                        }
                        lastTwoBytesSize = Math.max(0, lastTwoBytesSize - 1);
                        lastTwoBytes[lastTwoBytesSize++] = buffer[read - 1];
                    } else if (read >= 2) {
                        lastTwoBytesSize = 2;
                        lastTwoBytes[0] = buffer[read - 2];
                        lastTwoBytes[1] = buffer[read - 1];
                    }
                }

                if (read >= 0) {
                    bytes.write(buffer, 0, read);
                } else {
                    break;
                }
            }
        } finally {
            silentClose(bytes);
        }

        return bytes.toByteArray();
    }

    private byte[] readFile(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        try {
            return readInputStream(inputStream, false);
        } finally {
            silentClose(inputStream);
        }
    }

    private Request readRequest(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        return new Request(new String(readInputStream(inputStream, true)));
    }

    private static final class Request {
        private final String method;
        private final String uri;
        private final Map<String, String> headers;

        private Request(String request) {
            String[] lines = request.split("[\r\n]+");
            if (lines.length < 1) {
                throw new IllegalArgumentException();
            }

            String[] firstLineTokens = lines[0].split("\\s+");
            method = firstLineTokens[0];
            uri = firstLineTokens[1];
            headers = new HashMap<>();

            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                if (line.contains(":")) {
                    int sep = line.indexOf(':');
                    headers.put(line.substring(0, sep), line.substring(sep + 1).trim());
                }
            }
        }

        private String getMethod() {
            return method;
        }

        private String getUri() throws UnsupportedEncodingException {
            return uri;
        }

        @SuppressWarnings({"SameParameterValue", "unused"})
        private String getHeader(String name) {
            return headers.get(name);
        }
    }

    private static final class Response {
        private int statusCode;
        private final Map<String, String> headers = new HashMap<>();
        private byte[] body;

        private void setHeader(String name, String value) {
            headers.put(name, value);
        }

        private int getStatusCode() {
            return statusCode;
        }

        private void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            this.body = ("Error " + statusCode).getBytes();
        }

        private byte[] getBody() {
            return body;
        }

        private void setBody(byte[] body) {
            this.body = body;
        }

        private Map<String, String> getHeaders() {
            return Collections.unmodifiableMap(headers);
        }
    }

    public static void main(String[] args) throws IOException {
        new HttpServer(new File("static"), 8088);
    }
}
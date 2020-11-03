package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ru.itmo.web.lesson4.model.Color.*;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", GREEN),
            new User(6, "pashka", "Pavel Mavrin", RED),
            new User(9, "geranazarov555", "Georgiy Nazarov", BLUE),
            new User(11, "tourist", "Gennady Korotkevich", RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Codeforces Round #639: the round is rescheduled", "Hello, Codeforces!\n" +
                    "\n" +
                    "Unfortunately, I report that I decided to reschedule this round. I'm afraid that something might go wrong. Some strange database behavior has appeared, which leads to slow operation in completely unexpected places (it never was and should not be). I will work hard to fix it. My recent tests show that this can dramatically increase judging time (and leads to a huge queue). I don't want to risk the efforts of the writer, the coordinator, testers, and your time. Sorry about it, I was trying to fix it for many hours but I need more time. Hope it will be a great round!\n" +
                    "\n" +
                    "Mike.\n" +
                    "\n" +
                    "UPD 1: I temporarily rescheduled it on May, 6. But I'll discuss the date with the writer and coordinator and reschedule it again to the date they want.\n" +
                    "\n" +
                    "UPD 2: Monogon approved that среда, 6 мая 2020 г. в 17:35 is good new date and time for the round. See you in 3 days!\n" +
                    "\n" +
                    "UPD 3: It seems I've fixed the issue. It turned that it was connected with week ago incident. Now it is 4 AM and time to sleep.", 1),
            new Post(2, "EDU: Segment Tree, part 1", "EDU: Segment Tree, part 1 Hello everyone! I just published a [new lesson](https://codeforces.com/edu/course/2/lesson/4) in the EDU section. " +
                    "This is the first part of the lesson about the segment tree. <img src=\"/predownloaded/ad/f8/adf89a39c4a3c646e9b047bc1e24e894a01034d4.png\"/> In this lesson, we will learn how to build a simple segment tree (without mass modifications), " +
                    "and how to perform basic operations on it. We will also discuss some tasks that can be solved using the segment tree. <center style=\"margin:2.5em;\"> <a href=\"/edu/courses\" style=\"text-decoration:none; font-size:18px; background-color:#01579B; color:white; font-weight:bold; padding:0.5em 1em;\">" +
                    "Go to EDU &rarr;</a> </center> More about EDU section you can read in [this](/blog/entry/79530) post. Hope it will be helpful, enjoy!", 6),
            new Post(3, "KEK", "kek", 9),
            new Post(4, "Codeforces Round 672 (Div. 2)"
                    , "Tune in to my Twitch to watch me solve today's Codeforces Round 672 (Div. 2) virtually.\n" +
                    "\n" +
                    "UPD: Video on YouTube.",
                    11)
    );


    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }

        data.put("uri", request.getRequestURI());
    }
}

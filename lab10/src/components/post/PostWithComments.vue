<template>
    <div>
        <article>
            <a href="#" class="title">{{ post.title }}</a>
            <div class="information">By {{ users[post.userId].login }}, 16:42 02.06.2001</div>
            <div class="body">{{ post.text }}</div>
            <ul class="attachment">
                <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
            </ul>
            <div class="footer">
                <div class="left">
                    <img src="@/assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="@/assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="@/assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    16:42 02.06.2001
                    <img src="@/assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">{{ comments.length }}</a>
                </div>
            </div>
        </article>
        <article v-for="comment in comments" :key="comment.id">
            <div class="information">By {{ users[comment.userId].login }}, 16:42 02.06.2001</div>
            <div class="body">{{ comment.text }}</div>
        </article>
    </div>
</template>

<script>
export default {
    name: "PostWithComments",
    props: ["post", "users", "comments"]
}
</script>

<style scoped>

</style>
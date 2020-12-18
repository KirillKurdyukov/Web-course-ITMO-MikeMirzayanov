<template>
    <div>
        <article>
            <a href="#" class="title">{{ post.title }}</a>
            <div class="information">By {{ post.user.login }}, {{ post.creationTime }}</div>
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
                    <a href="#">{{ post.comments.length }}</a>
                </div>
            </div>
        <template v-if="user">
            <div class="form">
                <div class="header">Write Comment</div>
                <div class="body">
                    <form @submit.prevent="onCommentPost">
                        <div class="field">
                            <div class="name">
                                <label for="text">Comment</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text" v-model="text"></textarea>
                            </div>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Write">
                        </div>
                        <div>
                            {{ error }}
                        </div>
                    </form>
                </div>
            </div>
        </template>
        </article>
        <article v-for="comment in post.comments" :key="comment.id">
            <div class="information">By {{ comment.user.login }}, {{ post.creationTime }}</div>
            <div class="body">{{ comment.text }}</div>
        </article>
    </div>
</template>

<script>
export default {
    name: "PostWithComments",
    props: ["post", "user"],
    data: function () {
        return {
            text: "",
            error: ""
        }
    },
    methods : {
        onCommentPost: function () {
            this.error = "";
            this.$root.$emit("onCommentWrite", this.post.id, this.text);
        }
    }
}
</script>

<style scoped>

</style>
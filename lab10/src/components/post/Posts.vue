<template>
    <div>
        <Post v-for="post in posts" :post="post" :countComments="viewPostCountComments(post)" :user="viewPostUser(post)" :key="post.id"/>
    </div>
</template>

<script>
import Post from "@/components/post/Post";
export default {
    name: "Posts",
    components: {Post},
    props: ["posts", "comments", "users"],
    methods: {
        viewPostUser: function (post) {
            return this.users[post.userId];
        },
        viewPostCountComments: function (post) {
            return Object.values(this.comments).filter(c => c.postId === post.id).length;
        }
    }
}
</script>

<style scoped>

</style>
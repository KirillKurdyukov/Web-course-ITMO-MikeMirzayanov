<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="viewPostsIndex" :comments="comments" :users="users"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <PostWithComments v-if="page === 'PostWithComments'" :post="post" :users="users" :comments="viewPostComments"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "@/components/sidebar/Sidebar";
import Index from "@/components/middle/Index";
import Enter from "@/components/middle/Enter";
import WritePost from "@/components/middle/WritePost";
import EditPost from "@/components/middle/EditPost";
import Register from "@/components/middle/Register";
import Users from "@/components/middle/Users";
import PostWithComments from "@/components/post/PostWithComments";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: ""
        }
    },
    components: {
        PostWithComments,
        Users,
        Register,
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost
    },
    props: ["posts", "users", "comments"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
        viewPostsIndex: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        viewPostComments: function () {
            return Object.values(this.comments).filter((c) => c.postId === this.post.id)
        }
    },
    beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)

        this.$root.$on("onPost", (post) => this.post = post);
    }
}
</script>

<style scoped>

</style>
<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <PostWithComments v-if="page === 'PostWithComments'" :post="this.post" :user="user"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "@/components/sidebar/Sidebar";
import Index from "@/components/middle/Index";
import Enter from "@/components/middle/Enter";
import Register from "@/components/middle/Register";
import Users from "./middle/Users";
import WritePost from "./middle/WritePost";
import PostWithComments from "./post/PostWithComments";

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
        WritePost,
        Users,
        Register,
        Enter,
        Index,
        Sidebar
    },
    props: ["posts", "users", "user"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)

        this.$root.$on("onPost", (post) => this.post = post);

    }
}
</script>

<style scoped>

</style>
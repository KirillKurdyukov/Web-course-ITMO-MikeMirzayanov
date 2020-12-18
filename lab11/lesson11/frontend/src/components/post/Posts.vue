<template>
    <div>
        <Post v-for="post in posts" :post="post" :user="post.user" :key="post.id"/>
    </div>
</template>

<script>
import Post from "@/components/post/Post";
export default {
    name: "Posts",
    components: {Post},
    props: ["posts", "users"]
}
</script>

<style scoped>

</style>
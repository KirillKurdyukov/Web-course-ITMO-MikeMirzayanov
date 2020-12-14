<template>
    <Posts :posts="posts" :comments="comments" :users="users"/>
</template>

<script>
import Posts from "@/components/post/Posts";
export default {
    name: "Index",
    components: {Posts},
    props: ["posts", "comments", "users"]
}
</script>

<style scoped>

</style>
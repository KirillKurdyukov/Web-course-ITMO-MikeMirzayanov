<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2020 by Mike Mirzayanov
      | Number of registered users: {{ countUsers }}
      | Number of posts: {{ countPosts }}
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["countUsers", "countPosts"]
}
</script>

<style scoped>

</style>
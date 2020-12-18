<template>
    <section>
        <div class="header">
            {{ post.title }}
        </div>
        <div class="body">
            {{ post.text }}
        </div>
        <div class="footer">
            <a href="#" @click.prevent="onPost">View all</a>
        </div>
    </section>
</template>

<script>
export default {
    name: "SidebarPost",
    props: ["post"],
    methods: {
        onPost: function () {
            this.$root.$emit("onPost", this.post);
            this.$root.$emit("onChangePage", "PostWithComments");
        }
    }
}
</script>

<style scoped>
section {
    margin-bottom: 1rem;
}
</style>
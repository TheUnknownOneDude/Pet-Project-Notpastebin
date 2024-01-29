<template>
    <aside>
        <SidebarPost v-for="post in viewPosts" :post="post" :key="post.id"/>
    </aside>
</template>

<script>
import SidebarPost from "./SidebarPost";

export default {
    name: "Sidebar",
    components: {
        SidebarPost
    },
    props: ["posts"],

    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 6);
        }
    },
}
</script>

<style scoped>

</style>

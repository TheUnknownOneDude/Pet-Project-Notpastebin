<template>
    <section>
        <div>
            <a href="#" class="header">
                <router-link :to="{ name: 'Post', params: { id: post.id }}">
                {{ post.title }}
                </router-link>
            </a>
        </div>
        <div>
            <a href="#" class="header" >
                <router-link :to="{ name: 'User', params: { id: post.user.id }}">By user: {{ post.user.login}}</router-link>
            </a>
        </div>
    </section>
</template>

<script>
export default {
    name: "SidebarPost",
    props: ["post"],

    methods: {
        changePageWithParam: function (page, param) {
            this.$root.$emit("onChangePageWithParam", page, param);
        },
    }
}
</script>

<style scoped>
section {
    margin-bottom: 1rem;
}
</style>

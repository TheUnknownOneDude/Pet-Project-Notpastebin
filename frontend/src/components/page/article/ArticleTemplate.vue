<script setup>

</script>

<template>
    <article>
        <div class="title">
            <a href="#"><router-link :to="{ name: 'Post', params: { id: post.id }}">{{post.title.substring(0, 50)}}</router-link></a>
        </div>
        <div class="information">
            <a href="#"><router-link :to="{ name: 'User', params: { id: post.user.id }}">By user {{post.user.login}}</router-link></a></div>
        <div class="body">{{post.text.substring(0, 100)}}</div>
        <div class="footer">

            <div class="left">
                <img src="../../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                {{post.creationTime}}
            </div>
        </div>
    </article>
</template>

<script>
export default {
    name: 'ArticleTemplate',

    props: ["post"],
}
</script>

<style scoped>

</style>
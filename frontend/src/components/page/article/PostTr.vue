<script setup>

</script>

<template>
    <div class="users datatable">
        <div class="caption">User's posts</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
            </tr>
            </thead>
            <tbody>

            <tr v-for="post in posts" :key="post.id">
                <td class="id"> <a href="#"><router-link :to="{ name: 'Post', params: { id: post.id }}">{{ post.id }}</router-link></a></td>
                <td class="login"> <a href="#"><router-link :to="{ name: 'Post', params: { id: post.id }}">{{ post.title }}</router-link></a></td>
            </tr>

            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    name: "PostTr",

    props: ["posts", "user"],
}
</script>

<style scoped>

</style>
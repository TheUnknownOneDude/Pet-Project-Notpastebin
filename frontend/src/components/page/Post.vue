<script setup>

</script>

<template>
<!--    <article v-if="!isLoading && post.user.id != null && post.user.id !== this.post.user.id">-->
    <article v-if="this.post !== null && (this.user == null || this.user.id !== this.post.user.id)">
        <div class="title">
            <a>{{post.title}}</a>
        </div>
        <div class="information">
            By user <router-link :to="{ name: 'User', params: { id: post.user.id }}">{{post.user.login}}</router-link></div>
        <div class="body">
            <textarea readonly v-model="post.text"></textarea>
        </div>
        <div class="footer">

            <div class="left">
                <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                {{post.creationTime}}
            </div>
        </div>
    </article>

    <article v-else-if="this.user.id === this.post.user.id">
        <div class="title">
            Title:
            <input type="search" name="q" v-model="post.title">
        </div>
        <div class="body">
            <textarea v-model="post.text"></textarea>
        </div>
        <div>Publicity: <select v-model="post.publicity">
            <option>PUBLIC</option>
            <option>PRIVATE</option>
        </select></div>
        <div>Days until deletion: <input name="q" v-model="post.deletionInterval"></div>


        <div class="field error">{{ error }}</div>

        <div>
            <input type="submit" value="Edit Post" @click.prevent="editPost(post)">
        </div>
    </article>
</template>

<script>
import axios from "axios";

export default {

    data: function () {
        return {
            error: "",
            user: this.$store.getters.getUser,
            post: {
                id: 0,
                title: "",
                text: "",
                publicity: "PUBLIC",
                user: 0,
                creationTime: "",
                updateTime: "",
                deletionInterval: 1,
            },
        }
    },

    name: "Post",


    beforeCreate() {

        axios.get("http://localhost:8090/api/1/posts/post/" + this.$route.params.id).then(response => {
            this.post = response.data;
        });

        this.$root.$on("onWritePostValidationError", error => this.error = error);
    },



    methods: {
        editPost: function (post) {
            this.$root.$emit("onEditPost", post.id, post.title, post.text, post.publicity, post.user,
                post.creationTime, post.updateTime, post.deletionInterval);
        },
    }
}
</script>

<style scoped>

</style>
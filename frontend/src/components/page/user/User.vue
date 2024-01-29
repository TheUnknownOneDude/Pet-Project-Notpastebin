<script setup>

import PostTr from "@/components/page/article/PostTr.vue";
</script>

<template>
    <div>
        <div class="userbox">
            <img class="userimg" src="../../../assets/img/no-title.jpg" alt="no-title" title="No-title">
            <ul>
                <li>Id: {{ user.id }}</li>
                <li>Login: {{ user.login }}</li>
            </ul>
        </div>
        <PostTr :posts="posts" :user="user"/>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data: function () {
        return {
            user: {
                id: 0,
                login: "",
            },
            posts: [],
            error: "",
        }
    },

    name: "User",


    computed: {

    },

    beforeCreate() {
        axios.get("http://localhost:8090/api/1/users/user/" + this.$route.params.id).then(response => {
            this.user = response.data;
            axios.get("http://localhost:8090/api/1/posts/usersPosts?userId=" + this.user.id).then(response => {
                this.posts = response.data;
            });
        });
    },
}
</script>

<style scoped>

</style>
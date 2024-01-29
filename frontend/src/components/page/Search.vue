<template>
    <div>
        <ArticleTemplate v-for="post in posts" v-bind:key="post.id" :post="post"/>
    </div>
</template>

<script>
import ArticleTemplate from "@/components/page/article/ArticleTemplate.vue";
import axios from "axios";

export default {
    name: "Search",
    components: {ArticleTemplate},

    data: function () {
        return {
            query: "",
            posts: [],
        }
    },


    beforeCreate() {

        console.log(this.$route.params.query)
        axios.get("http://localhost:8090/api/1/posts/search/" + this.$route.params.query).then(response => {
            this.posts = response.data;
            this.$root.$emit("onChangePage", 'Search')
        }).catch(function (error) {
            console.log(error.toJSON());
        });
    }
}
</script>

<style scoped>

</style>
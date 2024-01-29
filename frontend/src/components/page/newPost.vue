<script setup>

</script>

<template>
    <article>
        <div class="title">
            Title:
            <input type="search" name="q" v-model="post.title">
        </div>
        <div class="body">
            Text:
            <textarea v-model="post.text">

            </textarea>

        </div>
        <div>Publicity: <select v-model="post.publicity">
            <option>PUBLIC</option>
            <option>PRIVATE</option>
        </select></div>
        <div>Days until deletion: <input name="q" v-model="post.deletionInterval"></div>


        <div class="field error">{{ error }}</div>

        <div>
            <input type="submit" value="Create Post" @click.prevent="writePost(post.title, post.text, post.publicity, post.deletionInterval)">
        </div>
    </article>
</template>

<script>
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

    name: "newPost",

    beforeCreate() {
        this.$root.$on("onWritePostValidationError", error => this.error = error);
    },



    methods: {
        writePost: function (title, text, publicity, deletionInterval) {
            this.$root.$emit("onWritePost", title, text, publicity, deletionInterval);
        },
    }
}
</script>
<style scoped>

</style>
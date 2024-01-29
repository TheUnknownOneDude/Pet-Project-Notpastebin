<template>
    <div class="middle">
        <Sidebar :posts="sidePosts"/>
        <main>
            <router-view></router-view>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            param: null
        }
    },
    components: {
        Sidebar
    },
    props: ["sidePosts", "user"],
    computed: {

    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.$router.push({name: page}).catch(()=>{}));

        this.$root.$on("onChangePageWithParam", (page, param) => this.$router.push({name: page, params: {param}}).catch(()=>{}));
    },
}
</script>

<style scoped>

</style>

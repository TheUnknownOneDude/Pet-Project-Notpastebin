<template>
    <div id="app">
        <Header/>
        <Middle :side-posts="sidePosts"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            sidePosts: [],
        }
    },

    methods: {
        updateAll() {
            this.$root.$emit("onUpdatePosts")
        },
        stopTimer () {
            if (this.interval) {
                window.clearInterval(this.interval)
            }
        },
        startTimer () {
            this.stopTimer()
            this.interval = window.setInterval(() => {
                this.updateAll()
            }, 100000)
        }
    },
    mounted () {
        this.startTimer()
    },
    beforeDestroy () {
        this.stopTimer()
    },

    beforeMount() {
        this.updateAll()

        axios.get("http://localhost:8090/api/1/posts/posts10").then(response => {
            this.posts = response.data;
            this.sidePosts = response.data;
        }).catch(function (error) {
            console.log(error.toJSON());
        });

    },
    beforeCreate() {

        if (localStorage.getItem("jwt")) {
            const jwt = localStorage.getItem("jwt")
            axios.get("http://localhost:8090/api/1/users/auth", { params: {
                jwt
            }}).then(response => {
                this.$store.dispatch('setUser', response.data)
            })
        }

        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }

            axios.post("http://localhost:8090/api/1/jwt", {
                    login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data)
                this.$root.$emit("onChangePage", 'Index')
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onRegister", (login, password) => {
            if (login === "") {
                this.$root.$emit("onRegisterValidationError", "Login is required");
                return;
            }

            if (password === "") {
                this.$root.$emit("onRegisterValidationError", "Password is required");
                return;
            }

            axios.post("http://localhost:8090/api/1/users/register", {
                login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data)
                this.$root.$emit("onChangePage", 'Index')
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onRegisterValidationError", error.response.data);
            });
        });

        this.$root.$on("onJwt", (jwt) => {
            this.$store.dispatch('setJwt', jwt)

            axios.get("http://localhost:8090/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.$store.dispatch('setUser', JSON.parse(JSON.stringify(response.data)))
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            this.$store.dispatch('setUser', null)
            localStorage.removeItem("jwt");
        });

        this.$root.$on("onWritePost", (title, text, publicity, deletionInterval) => {
            const userId = this.$store.getters.getUser.id
            const jwt = localStorage.getItem("jwt")
            axios.post("http://localhost:8090/api/1/posts/createPost?jwt=" + jwt, {
                title, text, publicity, userId, deletionInterval
            }).then(() => {
                this.$root.$emit("onChangePage", 'Index')
            }).catch(error => {
                this.$root.$emit("onWritePostValidationError", error.response.data);
            });
        })

        this.$root.$on("onEditPost", (id, title, text, publicity, user,
            creationTime, updateTime, deletionInterval) => {
            const jwt = localStorage.getItem("jwt")
            axios.post("http://localhost:8090/api/1/posts/editPost?jwt=" + jwt, {
                id, title, text, publicity, user,
                creationTime, updateTime, deletionInterval
            }).then(() => {
                this.$root.$emit("onChangePage", 'Index')
            }).catch(error => {
                this.$root.$emit("onWritePostValidationError", error.response.data);
            });
        })

        this.$root.$on("onUpdatePosts", () => {
            axios.get("http://localhost:8090/api/1/posts/posts10").then(response => {
                this.sidePosts = response.data;
            });
        });

    }
}
</script>

<style>
#app {

}
</style>

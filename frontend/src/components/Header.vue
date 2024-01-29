<template>
    <header>
        <a class="logo" href="#">
            <router-link to="/about"><img class="logo" src="../assets/img/Pastebin.com_logo_photo-resizer.ru.png" alt="Pastebin" title="Pastebin"/>
            Not Pastebin
            </router-link>
        </a>

        <a href="#">
            <router-link to="/newPaste">
            <img class="new_paste" src="../assets/img/new_paste.png" alt="New paste" title="New paste"/>
            </router-link>
        </a>
        <div class="languages">
            <a href="#"><img class="flag" src="../assets/img/gb.png" alt="In English" title="In English"/></a>
            <a href="#"><img class="flag" src="../assets/img/ru.png" alt="In Russian" title="In Russian"/></a>
        </div>
        <div class="enter-or-register-box">
            <template v-if="curUser">
                <a href="#"><router-link :to="{ name: 'User', params: { id: curUser.id }}">
                    {{ curUser.login }}
                    </router-link>
                </a>
                |
                <a href="#" @click.prevent="onLogout">Logout</a>
            </template>
            <template v-else>
                    <a href="#"> <router-link to="/enter">Enter</router-link></a>
                |
                    <a href="#"><router-link to="/register">Register</router-link></a>
            </template>
        </div>
        <nav>
            <ul>
                <li>
                    <a href="#">
                        <router-link to="/about">Home</router-link>
                    </a>
                </li>
                <li>
                    <a href="#"><router-link to="/users">Users</router-link></a></li>
                <li>
                    <input type="search" name="q" placeholder="Find paste..." v-model="query">
                    <input type="submit" value="Find" @click.prevent="searchPosts(query)">
                </li>

            </ul>
        </nav>
    </header>

</template>

<script>
export default {
    name: "Header",

    data: function () {
        return {
            query: ""
        }
    },

    computed: {
        curUser() {
            return this.$store.getters.getUser;
        },
    },

    methods: {

        searchPosts: function (query) {
            this.$router.push({name: 'Search', params: { query }}).catch(()=>{});
        },

        onLogout: function () {
            this.$root.$emit("onLogout");
            // this.$router.push({name: 'Index'}).catch(()=>{});
        }
    }
}
</script>

<style scoped>
  .flag {
    margin-left: 0.3rem;
  }
  .logo {
    max-width: 15rem;
  }
</style>

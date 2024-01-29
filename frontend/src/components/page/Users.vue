<script setup>

</script>

<template>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
            </thead>
            <tbody>

            <tr v-for="user in users" :key="user.id">
                <td class="id"><a href="#"><router-link :to="{ name: 'User', params: { id: user.id }}">{{ user.id }}</router-link></a></td>
                <td class="login"> <a href="#"><router-link :to="{ name: 'User', params: { id: user.id }}">{{ user.login }}</router-link></a></td>
            </tr>

            </tbody>
        </table>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "Users",

    data: function () {
        return {
            users: [],
        }
    },

    beforeCreate() {
        axios.get("http://localhost:8090/api/1/users/users").then(response => {
            this.users = response.data;
        }).catch(function (error) {
            console.log(error.toJSON());
        });
    }
}

</script>
<style scoped>

</style>
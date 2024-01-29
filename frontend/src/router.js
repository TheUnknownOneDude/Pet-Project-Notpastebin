import Vue from 'vue';
import VueRouter from 'vue-router';
import Post from "@/components/page/Post.vue";
import Index from "@/components/main/Index.vue";
// import App from "@/App.vue";
import Enter from "@/components/main/Enter.vue";
import Register from "@/components/main/Register.vue";
import Search from "@/components/page/Search.vue";
import User from "@/components/page/user/User.vue";
import Users from "@/components/page/Users.vue";
import newPost from "@/components/page/newPost.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        component: Index,
        meta: { title: 'О нас' }
    },
    {
        path: '/about',
        name: 'Index',
        component: Index,
        meta: { title: 'О нас' }
    },

    {
        path: '/paste/:id',
        name: 'Post',
        component: Post,
    },

    {
        path: '/newPaste',
        name: 'newPost',
        component: newPost,
    },

    {
        path: '/enter',
        name: 'Enter',
        component: Enter,
    },

    {
        path: '/register',
        name: 'Register',
        component: Register,
    },

    {
        path: '/search/:query',
        name: 'Search',
        component: Search,
    },

    {
        path: '/user/:id',
        name: 'User',
        component: User,
    },

    {
        path: '/users',
        name: 'Users',
        component: Users,
    },
];

const router = new VueRouter({
    routes,
    mode: 'history'
});

export default router;
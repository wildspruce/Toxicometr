import Vue from 'vue'
import Router from 'vue-router'
import ChatPage from "./components/ChatPage";

Vue.use(Router)

let router = new Router({
    routes: [
        {
            path: '/chat',
            name: 'Chat',
            component: ChatPage
        }
    ]
})

export default router

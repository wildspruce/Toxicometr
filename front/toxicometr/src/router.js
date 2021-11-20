import Vue from 'vue'
import Router from 'vue-router'
import ChatPage from "./components/ChatPage";
import HighlightingExample from './components/HighlightingExample.vue'

Vue.use(Router)

let router = new Router({
    routes: [
        {
            path: '/chat',
            name: 'Chat',
            component: ChatPage
        },
            path: '/hw',
            name: 'HighlightingExample',
            component: HighlightingExample
        }
    ]
})

export default router

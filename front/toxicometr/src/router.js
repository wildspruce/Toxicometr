import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'

Vue.use(Router)

let router = new Router({
    routes: [
        {
            path: '/hw',
            name: 'HelloWorld',
            component: HelloWorld
        },
    ]
})

export default router
import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "@/views/MainView.vue";
import AptMainView from "@/views/AptMainView.vue";
import BoardView from "@/views/BoardView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "AppMain",
    component: MainView,
  },
  {
    path: "/board",
    name: "board",
    component: BoardView,
  },
  {
    path: "/apt",
    name: "apt",
    component: AptMainView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

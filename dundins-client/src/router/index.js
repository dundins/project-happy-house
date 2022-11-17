import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "@/views/MainView.vue";
import AboutView from "@/views/AboutView.vue";
import AptMainView from "@/views/AptMainView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "AppMain",
    component: MainView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
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

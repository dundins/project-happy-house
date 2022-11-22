import Vue from "vue";
import VueRouter from "vue-router";
import BoardView from "@/views/BoardView.vue";
import BoardList from "@/components/board/BoardList.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "apt",
    component: () => import("@/views/ApartView"),
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView"),
  },
  {
    path: "/home",
    name: "home",
    component: () => import("@/views/HomeView"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/LoginComp"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: BoardView,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: BoardList,
      },
      {
        path: "write",
        name: "boardwrite",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boarddetail",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

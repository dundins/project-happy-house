import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "@/views/MainView.vue";
import AptMainView from "@/views/AptMainView.vue";

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
    component: () => import("@/views/BoardView"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      // {
      //   path: "write",
      //   name: "boardwrite",
      //   //beforeEnter: onlyAuthUser,
      //   component: () => import("@/components/board/BoardWrite"),
      // },
      // {
      //   path: "view/:articleno",
      //   name: "boardview",
      //   //beforeEnter: onlyAuthUser,
      //   component: () => import("@/components/board/BoardView"),
      // },
      // {
      //   path: "modify",
      //   name: "boardmodify",
      //   //beforeEnter: onlyAuthUser,
      //   component: () => import("@/components/board/BoardModify"),
      // },
      // {
      //   path: "delete/:articleno",
      //   name: "boarddelete",
      //   //beforeEnter: onlyAuthUser,
      //   component: () => import("@/components/board/BoardDelete"),
      // },
    ],
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

import Vue from "vue";
import VueRouter from "vue-router";
import BoardView from "@/views/BoardView.vue";
import BoardList from "@/components/board/BoardList.vue";

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("다시 로그인 후 시도해 주세요");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    next();
  }
};

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
    path: "/news",
    name: "news",
    component: () => import("@/views/NewsView"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView"),
    children: [
      {
        path: "/login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "/regist",
        name: "regist",
        component: () => import("@/components/user/UserRegist"),
      },
      {
        path: "/account",
        name: "account",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserAccount"),
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
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boarddetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete"),
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

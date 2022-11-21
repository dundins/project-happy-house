import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
// module import
import houseStore from "@/store/modules/houseStore";
import memberStore from "@/store/modules/memberStore";
import todoStore from "@/store/modules/todoStore";
import kakaoStore from "@/store/modules/kakaoStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    houseStore,
    memberStore,
    todoStore,
    kakaoStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

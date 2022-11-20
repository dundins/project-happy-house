import sig from "@/assets/json/sig.json";
import emd from "@/assets/json/emd.json";

const kakaoStore = {
  namespaced: true,
  state: {
    sigs: [],
    emds: [],
  },
  getters: {},
  mutations: {
    CLEAR_SIG_LIST(state) {
      state.sigs = [];
    },
    CLEAR_EMD_LIST(state) {
      state.emds = [];
    },
    SET_SIG_LIST(state, gugun) {
      const list = sig.features;
      for (let i = 0; i < list.length; i++) {
        if (list[i].properties.SIG_CD == gugun) {
          state.sigs.push(list[i].geometry.coordinates[0]);
          return;
        }
      }
    },
    SET_EMD_LIST(state, dong) {
      const list = emd.features;
      for (let i = 0; i < list.length; i++) {
        if (list[i].properties.EMD_CD + "00" == dong) {
          state.emds.push(list[i].geometry.coordinates[0]);
          return;
        }
      }
    },
  },
  actions: {
    // getSig: ({ commit }, gugun) => {
    //   commit("SET_SIG_LIST", { sigs: sig, gugun: gugun });
    // },
  },
};

export default kakaoStore;

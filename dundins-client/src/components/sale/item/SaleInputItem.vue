<template>
  <b-row class="mt-5">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="aptCode-group"
          label="아파트 코드"
          label-for="aptCode"
        >
          <b-form-input
            id="aptCode"
            v-model="article.aptCode"
            type="text"
            required
            placeholder="아파트 코드를 입력하세요."
          ></b-form-input>
        </b-form-group>
        <b-form-group id="title-group" label="제목" label-for="title">
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="매물을 소개하세요."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="content-group"
          class="mt-3 mb-3"
          label="내용"
          label-for="content"
        >
          <b-form-textarea
            id="content"
            v-model="article.content"
            type="text"
            required
            placeholder="매물에 대한 상세 설명을 입력하세요."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-form-group id="dealAmount-group" label="가격" label-for="dealAmount">
          <b-form-input
            id="dealAmount"
            v-model="article.dealAmount"
            type="text"
            required
            placeholder="가격을 입력하세요."
          ></b-form-input>
        </b-form-group>
        <b-form-group id="floor-group" label="층" label-for="floor">
          <b-form-input
            id="floor"
            v-model="article.floor"
            type="text"
            required
            placeholder="층을 입력하세요."
          ></b-form-input>
        </b-form-group>
        <b-form-group id="area-group" label="지역" label-for="area">
          <b-form-input
            id="area"
            v-model="article.area"
            type="text"
            required
            placeholder="지역을 입력하세요."
          ></b-form-input>
        </b-form-group>
        <b-form-group id="room-group" label="방 개수" label-for="room">
          <b-form-input
            id="room"
            v-model="article.room"
            type="text"
            required
            placeholder="방 개수를 입력하세요."
          ></b-form-input>
        </b-form-group>
        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >매물 등록</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >매물 수정</b-button
        >
        <b-button type="reset" variant="outline-danger" class="m-1"
          >초기화</b-button
        >
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeArticle, modifyArticle, getArticle } from "@/api/sale";
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
  name: "SaleInputItem",
  data() {
    return {
      article: {
        house_sale_id: 0,
        aptCode: 0,
        title: "",
        content: "",
        dealAmount: "",
        floor: "",
        area: "",
        room: 1,
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.house_sale_id;
      getArticle(
        param,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
    rows() {
      return this.articles.length;
    },
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.aptCode &&
        ((msg = "아파트 코드를 입력해주세요"),
        (err = false),
        this.$refs.aptCode.focus());
      err &&
        !this.article.title &&
        ((msg = "매물을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.article.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      err &&
        !this.article.floor &&
        ((msg = "층을 입력해주세요"), (err = false), this.$refs.floor.focus());
      err &&
        !this.article.area &&
        ((msg = "지역을 입력해주세요"), (err = false), this.$refs.area.focus());
      err &&
        !this.article.room &&
        ((msg = "방 개수를 입력해주세요"),
        (err = false),
        this.$refs.room.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.house_sale_id = 0;
      this.article.aptCode = 0;
      this.article.title = "";
      this.article.content = "";
      this.article.dealAmount = "";
      this.article.floor = "";
      this.article.area = "";
      this.article.room = 1;
      this.moveList();
    },
    registArticle() {
      let param = {
        aptCode: this.article.aptCode,
        userid: this.userInfo.userid,
        title: this.article.title,
        content: this.article.content,
        dealAmount: this.article.dealAmount,
        floor: this.article.floor,
        area: this.article.area,
        room: this.article.room,
      };
      writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        house_sale_id: this.article.house_sale_id,
        aptCode: this.article.aptCode,
        userid: this.userInfo.userid,
        title: this.article.title,
        content: this.article.content,
        dealAmount: this.article.dealAmount,
        floor: this.article.floor,
        area: this.article.area,
        room: this.article.room,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "salelist" });
    },
  },
};
</script>

<style></style>

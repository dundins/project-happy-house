<template>
  <v-tr>
    <v-td>{{ articleno }}</v-td>
    <v-th class="text-left">
      <router-link
        :to="{ name: 'boardview', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </v-th>
    <v-td>{{ hit }}</v-td>
    <v-td>{{ userid }}</v-td>
    <v-td>{{ regtime | dateFormat }}</v-td>
  </v-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>

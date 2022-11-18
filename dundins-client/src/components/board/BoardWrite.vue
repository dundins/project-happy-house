<template>
  <v-container>
    <v-row>
      <v-col>
        <h3>글작성</h3>
      </v-col>
    </v-row>
    <board-input-item type="register" />
  </v-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>

<template>
  <b-container id="bc" class="bv-example-row mt-5">
    <board-input-item type="register" />
  </b-container>
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

<style>
#bc {
  width: 65%;
}
</style>

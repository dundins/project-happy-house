<template>
  <b-container id="bc" class="bv-example-row mt-3">
    <sale-input-item type="modify" />
  </b-container>
</template>

<script>
import SaleInputItem from "@/components/sale/item/SaleInputItem";

export default {
  name: "SaleModify",
  components: {
    SaleInputItem,
  },
};
</script>

<style>
#bc {
  width: 65%;
}
</style>

<template>
  <b-container id="bc" class="bv-example-row mt-5">
    <sale-input-item type="register" />
  </b-container>
</template>

<script>
import SaleInputItem from "@/components/sale/item/SaleInputItem";

export default {
  name: "SaleWrite",
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

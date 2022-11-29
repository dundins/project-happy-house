<script>
import { deleteArticle } from "@/api/sale";
import SaleList from "@/components/sale/SaleList";

export default {
  name: "SaleDelete",
  components: { SaleList },
  created() {
    let param = this.$route.params.house_sale_id;
    deleteArticle(
      param,
      ({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "salelist" });
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>

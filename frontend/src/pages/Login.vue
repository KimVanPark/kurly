<template>
  <v-container>
    <v-row>
      <v-col>
        <v-btn @click="onClickButton" block>
          피킹하러 가기
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <div>
          {{ uuid }}
        </div>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import api from "@/api";

export default {
  name: "Login",
  data() {
    return {
      uuid: "hi"
    }
  },
  methods: {
    onClickButton() {
      this.$router.push("picking")
    }
  }
}
</script>

<style scoped>

</style>

<template>
  <v-container>
    <v-row>
      <v-col>
        여기는 피킹화면
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn @click="onClickButton" block>
          api 테스트하기
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <div>
          api 결과 값 : {{ uuid }}
        </div>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import api from "@/api";
import {mapGetters} from "vuex";

export default {
  name: "Picking",
  data() {
    return {
      uuid: "hi"
    }
  },
  computed: {
    ...mapGetters(['getWorker'])
  },
  methods: {
    async onClickButton() {
      try {
        const {data} = await api.request({
          methods: "get",
          url: "/test"
        })
        this.uuid = data;
      } catch (e) {
        console.error("error!!")
      }
    }
  },
  mounted() {
    console.log("worker is",this.getWorker)
  }
}
</script>

<style scoped>

</style>

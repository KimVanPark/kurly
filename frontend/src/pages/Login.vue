<template>
  <v-container fluid class="fill-height">
    <h1 class="kvp-banner">
      KVP X Kurly
    </h1>
    <v-row class="mb-10">
      <v-col>
        <v-row class="flex-column">
          <v-col class="pa-0 pl-5 text-start">
              <span class="info-text">
                작업자 정보를 입력해주세요.
              </span>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pb-0">
            <v-text-field
                ref="workerInput"
                v-model="worker"
                solo
                label="작업자 입력"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-btn color="#92A2EA"
                   @click="onClickButton" block>
              <span class="start-picking-button">
                피킹하러 가기
              </span>
            </v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-snackbar
        v-model="loginError.snackbar"
        timeout="1000"
        color="red accent-2"
    >
      {{ loginError.text }}
    </v-snackbar>
  </v-container>
</template>

<script>
import {mapGetters} from "vuex";
import API from "@/api/worker";

export default {
  name: "Login",
  data() {
    return {
      worker: "",
      loginError: {
        snackbar: false,
        text: '로그인에 실패하였습니다. 작업자를 확인해주세요.'
      }
    }
  },
  computed: {
    ...mapGetters(['getWorker'])
  },
  methods: {
    async onClickButton() {
      if (!this.worker) {
        this.loginError.text = '작업자를 입력해주세요'
        this.loginError.snackbar = true
        return;
      }
      const params = {
        name: this.worker
      }
      try {
        const data = await API.fetchWorker(params);
        this.$store.commit("setWorker", {
          id: data.id,
          name: data.name,
          level: data.level
        })
      } catch (e) {
        this.loginError.text = '로그인에 실패하였습니다. 작업자를 확인해주세요.'
        this.loginError.snackbar = true
        return;
      }

      await this.$router.push("picking")
    }
  },
  mounted() {
    this.$refs.workerInput.focus();
  }
}
</script>

<style scoped lang="scss">
.info-text {
  font-size: 16px;
  font-weight: bold;
}

.kvp-banner {
  position: absolute;
  top: 10px;
}

.start-picking-button {
  color: white;
  font-size: 16px;
  font-weight: bold;
}
</style>

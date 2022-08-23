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
                :background-color="workerInputBackgroundColor"
                :readonly="isLoggedIn"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row v-if="isLoggedIn">
          <v-col class="pa-0 pr-5 text-end">
            <span>현재 숙련포인트 : {{ getWorker.point }}</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-btn color="#92A2EA"
                   v-if="!isLoggedIn"
                   @click="onClickButton" block>
              <span class="start-picking-button">
                로그인 하기
              </span>
            </v-btn>
            <v-btn color="#92A2EA"
                   v-if="isLoggedIn"
                   @click="onClickPickingButton" block>
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
    <v-snackbar
        v-model="pointAlert.snackbar"
        timeout="3000"
        color="green accent-2"
    >
      {{ pointAlert.text }}
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
      },
      pointAlert: {
        snackbar: false,
        text: '로그인에 실패하였습니다. 작업자를 확인해주세요.'
      }
    }
  },
  computed: {
    ...mapGetters(['getWorker']),
    isLoggedIn() {
      return !!this.getWorker.id
    },
    workerInputBackgroundColor() {
      if (this.isLoggedIn) {
        return "grey lighten-2"
      }
      return ""
    }
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
          level: data.level,
          point: data.point
        })
        const totalChangedPoint = data.points.reduce((a, b) => a + b.point, 0);
        if (totalChangedPoint !== 0) {
          this.pointAlert.text = `${totalChangedPoint}만큼 숙련포인트가 변경되었습니다.`
          this.pointAlert.snackbar = true
        }
      } catch (e) {
        this.loginError.text = '로그인에 실패하였습니다. 작업자를 확인해주세요.'
        this.loginError.snackbar = true
        return;
      }
    },
    async onClickPickingButton() {
      await this.$router.push("picking")
    }
  },
  mounted() {
    this.$refs.workerInput.focus();

    if (this.isLoggedIn) {
      this.worker = this.getWorker.name
    }
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

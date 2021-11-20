<template>
  <v-card
      color="#131c28"
      class="fill-height mx-12"
      height="100%"
  >
    <Modal v-show="isModalVisible"
           @changeUsername="changeUsername($event)"
    />
    <v-card-title style="color: white;" class="justify-center">Welcome to the chat!</v-card-title>

    <virtual-list
        class="list"
        style="height: 360px; overflow-y: auto;"
        :data-key="'uid'"
        :data-sources="received_messages"
        :data-component="mc"
        :estimate-size="50"
    />

    <v-spacer></v-spacer>
    <highlightable-input
        align="left"
        label="Write something"
        style="color: white; width: auto; border: 2mm ridge rgba(170, 50, 220, .6); font-size: 22px"
        class="mx-16"
        placeholder="Write something"
        append-icon="send"

        :highlight-style="defaultStyle"
        :highlight-enabled="highlightEnabled"
        :highlight="highlight"
        :caseSensitive="caseEnabled"

        v-model="message"
        v-on:keyup.enter="send(message)"
    />
  </v-card>
</template>

<script>

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import VirtualList from "vue-virtual-scroll-list";
import Vuetify from 'vuetify';
import Vue from 'vue';

import Modal from "./Modal";
import MessageComponent from "./Message";
import HighlightableInput from "@/HighlightableInput";
import badWords from 'raw-loader!@/assets/badWords';

Vue.use(Vuetify)

export default {
  name: "websocketdemo",
  components: {
    HighlightableInput,
    Modal,
    VirtualList
  },
  data() {
    const wordList = badWords.split('\n')

    return {
      msg: '',
      defaultStyle: {'background-color': '#ff073a', 'color': 'white'},
      highlight: wordList,
      highlightEnabled: true,
      caseEnabled: false,
      customHighlight: '',

      name: null,
      mc: MessageComponent,
      received_messages: [],
      message: null,
      send_message: null,
      connected: false,
      isModalVisible: false,
    };
  },
  mounted() {
    if (Stomp.status !== 'CONNECTED') {
      this.showModal()
      // do stuff that requires a connection, like establish subscriptions
    }
  },
  watch: {
    name: function (val) {
      this.name = val
      console.log(val)
    },
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
    changeUsername(username) {
      this.name = username;
      this.send_message = username;
      this.closeModal();
      this.connect()
    },
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {text: this.message};
        this.stompClient.send("/app/income", JSON.stringify(msg), {});
      }
    },
    connect() {
      this.socket = new SockJS("http://localhost:9000/toxicometr");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
          {},
          frame => {
            this.connected = true;
            console.log(frame);
            this.stompClient.subscribe("/topic/outcome", tick => {
              this.received_messages.push({uid: tick.headers["message-id"], text: JSON.parse(tick.body).text});
            });
          },
          error => {
            console.log(error);
            this.connected = false;
          }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    }
  }
};
</script>

<style scoped>

</style>
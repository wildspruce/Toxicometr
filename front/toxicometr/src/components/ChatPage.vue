<template>
  <v-card
      color="#131c28"
      class="fill-height"
      height="100%"

  >
    <Modal v-show="isModalVisible"

           @changeUsername="changeUsername($event)"
    />
    <v-card-title style="color: white; font-size: 1.8rem;" class="justify-center">Welcome to the chat!</v-card-title>

    <virtual-list
        class="list"
        style="height: 660px;  background: linear-gradient(#500e3d, #1b1b1f); overflow-y: auto;"
        :data-key="'uid'"
        :data-sources="received_messages"
        :data-component="mc"
        :estimate-size="50"
    />

    <v-spacer></v-spacer>
    <v-row align="end" class="mx-10">
      <v-col
          sm="10"
          md="11"
          xs="9">
        <highlightable-input
            align="left"
            label="Write something"
            class="ml-1"
            placeholder="Write something"
            append-icon="send"
            @input="autoSave"

            :highlight-style="defaultStyle"
            :highlight-enabled="highlightEnabled"
            :highlight="highlight"
            :caseSensitive="caseEnabled"
            v-bind:style="inputTextAreaStile"
            v-model="message"
            v-on:keyup.enter="send()"
        />
      </v-col>
      <v-col sm="2"
             md="1"
             xs="3"
             class="text-left"
             justify="start"
             align-self="center">
        <v-btn color="#0d1522" x-large style="elevation: higher; border: 2mm ridge rgba(170, 50, 220, .6);" dark
               v-on:click="send()">Send
        </v-btn>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import VirtualList from "vue-virtual-scroll-list";
import Vuetify from 'vuetify';
import Vue from 'vue';
import Api from '@/services/api'

import Modal from "./Modal";
import MessageComponent from "./Message";
import HighlightableInput from "@/HighlightableInput";
import badWords from 'raw-loader!@/assets/badWords';

Vue.use(Vuetify)

const _ = require('lodash');

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
      lastMsg: "",

      name: null,
      mc: MessageComponent,
      received_messages: [],
      tone: "",
      message: "",
      send_message: null,
      connected: false,
      isModalVisible: false,
      toneImgUrl: "",
      inputTextAreaStile: {
        color: 'white',
        width: 'auto',
        background: '99.5% 50% / 2% 80% no-repeat',
        paddingLeft: '30px',
        border: '2mm ridge rgba(170, 50, 220, .6)',
        fontSize: '22px'
      }
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
    autoSave: _.debounce(function () {
      console.log(this.message)
      this.getTone(this.message)
      this.setImgByTone()
    }, 500),

    setImgByTone() {
      switch (this.tone) {
        case "EXCITED":
          this.toneImgUrl = "'https://i.ibb.co/6BjH7R0/pic-excited.png'"
          break;
        case "FRUSTRATED":
          this.toneImgUrl = "'https://i.ibb.co/xfqL0GF/pic-frustrated.png'"
          break;
        case "IMPOLITE":
          this.toneImgUrl = "'https://i.ibb.co/DgGHDFb/pic-inpolite.png'"
          break;
        case "POLITE":
          this.toneImgUrl = "'https://i.ibb.co/Lg98n3P/pic-polite.png'"
          break;
        case "SAD":
          this.toneImgUrl = "'https://i.ibb.co/FzjqkPt/pic-sad.png'"
          break;
        case "SATISFIED":
          this.toneImgUrl = "'https://i.ibb.co/vBNG03B/pic-satisfied.png'"
          break;
        case "SYMPATHETIC":
          this.toneImgUrl = "'https://i.ibb.co/CnsdZr7/pic-sympathetic.png'"
          break;
        default:
          this.toneImgUrl = ""
          break;
      }

      this.inputTextAreaStile = {
        color: 'white',
        width: 'auto',
        background: '99.5% 50% / 2% 80% no-repeat url(' + this.toneImgUrl + ')',
        paddingLeft: '30px',
        border: '2mm ridge rgba(170, 50, 220, .6)',
        fontSize: '22px'
      }

      console.log("Tone: " + this.tone)
      console.log(this.inputTextAreaStile)
    },

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
        const msg = {text: this.message, author: this.name};
        this.stompClient.send("/app/income", JSON.stringify(msg), {});
        this.lastMsg = this.msg
        this.message = ""
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
              console.log(tick.body)
              console.log("Json Parse " + JSON.parse(tick.body))
              console.log("Last msg " + this.lastMsg)
              let isleft = true
              if (this.lastMsg === JSON.parse(tick.body).text) {
                isleft = false
              }
              this.received_messages.push({
                uid: tick.headers["message-id"],
                content: JSON.parse(tick.body),
                left: isleft
              });
            });
          },
          error => {
            console.log(error);
            this.connected = false;
          }
      );
    },

    getTone(message){
      Api().get(`/tone/${message}`).then(res => {
        console.log("res was: " + res.data.tone)
        this.tone = res.data.tone
      })
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
/*div {background: linear-gradient(#1d1d24, #f84fce);}*/

</style>
<template>
  <v-app style="margin:30px">
    <v-container>
      <h3> Highlight and style specific words as you're typing. </h3>

      <highlightable-input
          align="left"
          class="myinput"
          style="color: #e2e1ee; background-color: #1a222c"
          data-placeholder="Try typing any of the words below like hacker news or @Soup"
          :highlight-style="defaultStyle"
          :highlight-enabled="highlightEnabled"
          :highlight="highlight"
          :caseSensitive="caseEnabled"
          v-model="msg"
      />
    </v-container>
  </v-app>
</template>

<script>

import Vuetify from 'vuetify'
import Vue from 'vue'
import HighlightableInput from "@/HighlightableInput";
import badWords from 'raw-loader!@/assets/badWords';

Vue.use(Vuetify)

export default {
  name: 'HighlightingExample',
  components: {
    HighlightableInput
  },
  data() {
    const wordList = badWords.split('\n')
    console.log(wordList)
    return {
      msg: '',
      defaultStyle: {'background-color': '#ff073a', 'color': 'white'},
      highlight: wordList,
      highlightEnabled: true,
      caseEnabled: false,
      customHighlight: ''
    }
  },
  methods: {
    handleNewHighlights() {
      // Ugly hack because chrome is stupid
      // https://stackoverflow.com/questions/26962323/what-is-this-insane-space-character-google-chrome
      var h = this.customHighlight.replace(new RegExp(String.fromCharCode(32), "g"), String.fromCharCode(160));
      if (h.length > 0)
        this.highlight.unshift(h)
      this.customHighlight = ""
    }
  }
}
</script>

<style scoped>

</style>

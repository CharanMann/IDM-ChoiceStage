<template>
    <div>
        <h5>{{$t("pages.selfservice.registration.choice.title")}}</h5>

        <b-form-select v-model="selected" :options="choices" />

        <div class="mt-3">Selected OTP delivery mode: {{ selected }}</div>

        <b-button @click="save" :block="true" size="lg" variant="primary">
            {{$t("pages.selfservice.registration.choice.select")}}
        </b-button>
    </div>
</template>

<script>
    export default {
      name: 'Choice',
      props: {
          selfServiceDetails: { required: true }
      },
      data () {
          return {
              selected: null,
              choices: this.selfServiceDetails.requirements.definitions.Choices.oneOf
          }
      },
      methods: {

        getData () {
            return {
                selectedChoice: this.selected
            };
        },

        save () {
            this.$emit('advanceStage', this.getData());
        },
      }
    };
</script>

<style scoped></style>

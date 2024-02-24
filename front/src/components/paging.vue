<template>
  <div class="page_wrap">
    <div class="page_nation">
      <a class="arrow pprev" @click="firstPage()"></a>
      <a class="arrow prev" @click="prevPage()"></a>
      <a
        href="#"
        v-for="item in countInPages"
        :key="item"
        :class="startPage - 1 + item == page.page ? 'active' : null"
        @click="selectPage(startPage - 1 + item)"
      >
        {{ startPage - 1 + item }}</a
      >
      <a class="arrow next" href="#" @click="nextPage()"></a>
      <a class="arrow nnext" href="#" @click="endPage()"></a>
    </div>
  </div>
</template>
<script>
export default {
  name: 'PagingView',
  props: {
    page: {
      type: Object,
      default() {
        return {
          total: 0,
          page: 0,
          count: 0
        }
      }
    },
    defaultPages: {
      type: Number,
      default: 10 // 화면에 보여줄 페이징 갯수
    }
  },
  computed: {
    startPage: function () {
      let value = Math.floor((this.page.page - 1) / this.defaultPages) * this.defaultPages + 1
      return value
    },
    totalPage: function () {
      var count = this.page.count ? this.page.count : 10
      return Math.floor(this.page.total / count) + (this.page.total % count == 0 ? 0 : 1)
    },
    countInPages: function () {
      let currPages = this.totalPage - (this.startPage - 1)
      if (currPages < this.defaultPages) {
        return currPages
      } else {
        return this.defaultPages
      }
    }
  },
  methods: {
    selectPage: function (page) {
      this.$emit('onPage', page)
    },
    prevPage: function () {
      if (this.page.page == 1) {
        return
      }
      this.selectPage(this.page.page - 1)
    },
    nextPage: function () {
      if (this.page.page == this.totalPage) {
        return
      }
      this.selectPage(Number(this.page.page) + 1)
    },
    firstPage: function () {
      this.selectPage(1)
    },
    endPage: function () {
      if (this.page.page == this.totalPage) {
        return
      }
      this.selectPage(this.totalPage)
    }
  }
}
</script>

<style>
.page_wrap {
  text-align: center;
  font-size: 0;
  margin-top: 30px;
}
.page_nation {
  display: inline-block;
}
.page_nation .none {
  display: none;
}
.page_nation a {
  display: block;
  margin: 0 3px;
  float: left;
  border: 1px solid #e6e6e6;
  width: 28px;
  height: 28px;
  line-height: 28px;
  text-align: center;
  background-color: #fff;
  font-size: 13px;
  color: #999999;
  text-decoration: none;
  border-radius: 5px; /* 버튼 모서리를 둥글게 만듭니다. */
}
.page_nation .arrow {
  border: 1px solid #ccc;
}
.page_nation .pprev {
  background: #f8f8f8 url('./public/page_pprev.png') no-repeat center center;
  margin-left: 0;
}
.page_nation .prev {
  background: #f8f8f8 url('./public/page_prev.png') no-repeat center center;
  margin-right: 7px;
}
.page_nation .next {
  background: #f8f8f8 url('./public/page_next.png') no-repeat center center;
  margin-left: 7px;
}
.page_nation .nnext {
  background: #f8f8f8 url('./public/page_nnext.png') no-repeat center center;
  margin-right: 0;
}
.page_nation a.active {
  background-color: #0052a4;
  color: #fff;
  border: 1px solid #fff;
}
</style>

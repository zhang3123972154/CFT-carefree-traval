<script setup>
	import { onLaunch, onShow, onHide } from '@dcloudio/uni-app'
	import usePhoneInforStore from "./store/phoneInfor";

	const phoneInforStore = usePhoneInforStore();

	onLaunch(() => {
		console.log('App Launch!')
		const infor = uni.getWindowInfo();
		console.info(infor);
		console.info(infor.statusBarHeight);
		// console.info(infor.windowWidth);
		phoneInforStore.setStatusBarHeight(infor.statusBarHeight);
		phoneInforStore.setPhoneWidth(infor.windowWidth);

		// css global
		// document.documentElement.style.setProperty('--status-height', infor.statusBarHeigh + 'px');
	})
	onShow(() => {
		console.log('App Show!')
	})
	onHide(() => {
		console.log('App Hide!')
	})
</script>


<style lang="scss">
	/*每个页面公共css */
	@import "uni_modules/uview-plus/index.scss";
	@import "./css/flexCF.css";
	@import "./css/flex.css";
	@import "./css/animation.css";
	@import "./css/text.css";
	@import "./css/position.css";
	@import "./css/maggin.css";
	
	.top-container {
		padding-top: var(--status-height);
	}
	
</style>

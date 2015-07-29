<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<%@include file="../common/common_no_head.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/regist/regist.css' type='text/css' />
<script type="text/javascript">
function toIndex(){
	window.location.href = _rootUrl + "/main/index.htm";
}
</script>
<style type="text/css">
	#content { width: 1000px; margin: auto; padding: 0px 100px 50px 100px; color: #666; background: #fff; box-shadow: 0px 4px 4px #cbc7c4; font-size: 14px; margin-top: 20px;}
	#content_txt { padding: 50px 0px 35px 0px; font-size: 25px;}
	.slsfwtk { padding-bottom: 10px;}
	.p_line_height { line-height: 26px;}
	.content_title { font-weight: bold; padding-top: 24px;}
</style>
</head>
<body>
	
	<div id="regist_head">
		<ul id="rh_ul" class="clear">
			<li id="sls_log" onclick="toIndex();"></li>
			<li id="sls_txt"></li>
		</ul>
	</div>
	
	<div id="content">
		<div id="content_txt">用户协议</div>
		
		<div class="slsfwtk">手拉手服务条款：</div>
		
		<p class="p_line_height">《用户注册协议》（以下简称“本协议”）由手拉手（北京）社区服务有限公司，域名www.365020.com（以下简称“手拉手”）的网站服务时与手拉手用户达成的关于使用手拉手网站的各项规则、条款和条件。本协议在手拉手用户接受注册时生效。您在成为手拉手用户前，必须仔细阅读并接受本协议中所述的所有规则、条款和条件，包括因被提及而纳入的条款和条件。强烈建议您阅读本协议时，同时阅读本协议中提及的其他网页所包含的内容，因为其可能包含对作为手拉手用户的您适用的进一步条款和条件。</p>
		
		<div class="content_title">一、 用户注册</div>
		<p class="p_line_height">1. 用户注册是指用户登录手拉手网站，按要求填写相关信息并确认同意履行相关用户协议的过程。用户因进行交易、获取有偿服务等而发生的所有应纳税赋由用户自行承担责任。</p>
		<p class="p_line_height">2. 手拉手用户必须是具有完全民事行为能力的自然人，或者是具有合法经营资格的实体组织。无民事行为能力人、限制民事行为能力人以及无经营或特定经营资格的组织不当注册为手拉手用户或超过其民事权利或行为能力范围从事与手拉手进行交易的，其与手拉手之间的服务协议自始无效，一经发现，手拉手有权立即停止与该用户的交易、注销该用户，并追究其使用手拉手“服务”的一切法律责任。</p>
	
		<div class="content_title">二、 商品交易</div>
		<div class="slsfwtk">用户在手拉手购买商品时必须遵守以下条款：</div>
		<p class="p_line_height">1. 用户在使用手拉手服务时应遵守所有适用的中国法律、法规、条例和地方性法律的要求。用户还必须确保遵守本协议及纳入本协议的所有其他条款和规则的规定。</p>
		<p class="p_line_height">2. 如果用户在手拉手购买商品，用户有义务完成与手拉手的交易（法律或本协议禁止的交易除外）。通过对一项商品所下的订单，用户同意受该商品描述所含的出售条件的约束，只要该出售条件不违反法律或本协议规定。</p>
		<p class="p_line_height">3. 用户通过手拉手网站订购商品，订单即为购买商品的申请或要约。但只有当手拉手向用户发出确认收到订单及送货确认内容的电子邮件，或直接将商品发送至用户指定地址时，才构成手拉手对该订单的承诺，此时合同即告成立。</p>
		<p class="p_line_height">4. 手拉手有权在发现手拉手网站上显示的商品及订单明显错误或缺货的情况下，单方面撤回这些信息或撤销该订单。手拉手保留对商品订购数量的限制权。在用户下订单的同时，也同时承认了其已经达到购买所订商品的法定年龄，并对其在订单中提供的所有信息的真实性负责。</p>
		<p class="p_line_height">5. 商品价格和可获性都将在手拉手网站上标明，显示的每一项价格都包含了增值税（按照法定税率）。送货费将另外结算，费用根据用户选择的送货方式的不同而异。手拉手有权随时更改这些信息而不做任何通知。</p>
		
		<div class="content_title">三、 用户的权利和义务</div>
		<p class="p_line_height">1. 用户有权拥有其在手拉手的用户名及密码，并有权使用自己的用户名及密码随时登录手拉手网站。用户不得以任何形式转让或授权他人使用自己的手拉手用户名。</p>
		<p class="p_line_height">2. 用户有权根据本协议的规定以及手拉手网站上发布的相关规则在手拉手网站上查询商品信息、发表使用体验、参与商品讨论、邀请关注好友、上传商品图片、参加手拉手的有关活动，以及享受手拉手提供的其它信息服务。</p>
		<p class="p_line_height">3. 用户有义务在注册和通过电话购物时提供自己的真实资料，并保证诸如电子邮件地址、联系电话、联系地址、邮政编码等内容的有效性及安全性，保证手拉手可以通过上述联系方式与用户本人进行联系。同时，用户也有义务在相关资料实际变更时及时更新有关注册资料。用户保证不以他人资料在手拉手进行注册和购买行为。</p>
		<p class="p_line_height">4. 手拉手只收集由您在自主意愿下所填写、传送，同时愿意由手拉手所使用的资料，包括姓名，地址、电子邮件信箱等项目，其使用方式将包括：利用资料了解您对我们的网站商品及服务的意见和看法，或是参加由手拉手所举办的网络活动等等。在您同意的情况下，手拉手会通过电子邮件或者手机短信的形式，不定期发送相关服务信息给您，包括手拉手的订单信息，配送信息、优惠活动、手机密码找回等相关服务信息。如果您在手拉手网站注册时，选择不接受相关讯息，或收到电子邮件或手机短信是选择退订，那么手拉手就不再发送此类信息给您。在个别情况下，手拉手的客服专员可能会致电手拉手的会员了解购物体验，从而改进我们的服务。如果会员在电话访问中表明不希望参与此类电话访问，手拉手将会立即停止对该会员的电话访问。</p>
		<p class="p_line_height">5. 用户应当保证在使用手拉手购买商品过程中遵守诚实信用原则，不在购买过程中采取不正当行为，不扰乱网上交易的正常秩序。</p>
		<p class="p_line_height">6. 用户享有言论自由权利；并拥有适度修改、删除自己发表的文章的权利。用户不得在手拉手发表包含以下内容的言论：</p>
		<p class="p_line_height">（一）煽动、抗拒、破坏宪法和法律、行政法规实施的；以下相同情况处理；</p>
		<p class="p_line_height">（二）煽动颠覆国家政权，推翻社会主义制度的；</p>
		<p class="p_line_height">（三）煽动、分裂国家，破坏国家统一的；</p>
		<p class="p_line_height">（四）煽动民族仇恨、民族歧视，破坏民族团结的；</p>
		<p class="p_line_height">（五）任何包含对种族、性别、宗教、地域内容等歧视的；</p>
		<p class="p_line_height">（六）捏造或者歪曲事实，散布谣言，扰乱社会秩序的；</p>
		<p class="p_line_height">（七）宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；</p>
		<p class="p_line_height">（八）公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；</p>
		<p class="p_line_height">（九）损害国家机关信誉的；</p>
		<p class="p_line_height">（十）其他违反宪法和法律行政法规的；</p>
		<p class="p_line_height">7. 用户在发表使用体验、讨论图片等，除遵守本条款外，还应遵守该讨论区的相关规定。</p>
		<p class="p_line_height">8. 未经手拉手同意，禁止用户在网站发布任何形式的广告。</p>
		
		<div class="content_title">四、 手拉手的权利和义务</div>
		<p class="p_line_height">1. 手拉手有义务在现有技术上维护整个网上交易平台的正常运行，并努力提升和改进技术，使用户网上交易活动得以顺利进行。</p>
		<p class="p_line_height">2. 对用户在注册使用手拉手网上交易平台中所遇到的与交易或注册有关的问题及反映的情况，手拉手应及时作出回复。</p>
		<p class="p_line_height">3. 对于用户在手拉手网上交易平台上的不当行为或其它任何手拉手认为应当终止服务的情况，手拉手有权随时作出删除相关信息、终止提供服务等处理，而无须征得用户的同意。</p>
		
		<div class="content_title">五、 网站规则、修改和可分性</div>
		<div class="slsfwtk">只有已提交的订单，手拉手才能确认商品的价格。手拉手将尽最大努力保证用户所购商品与网站和目录上公布的价格一致，但价目表和声明并不构成要约。尽管做出最大的努力，手拉手的商品中一小部分商品可能会有定价错误。如果发现错误定价，手拉手将采取下列措施之一：</div>
		<p class="p_line_height">1. 价格变动规则</p>
		<p class="p_line_height">① 如果某一商品的正确定价低于错误定价，手拉手将按照较低的定价向用户销售该商品。</p>
		<p class="p_line_height">② 如果某一商品的正确定价高于错误定价，手拉手会根据具体情况决定，是否在交付前联系用户寻求指示, 或者取消订单并通知用户。</p>
		<p class="p_line_height">2. 商品缺货规则</p>
		<p class="p_line_height">用户希望购买的商品如果发生缺货，手拉手有权取消订单，手拉手会尽最大努力在最快时间内满足用户的购买需求</p>
		<p class="p_line_height">3. 邮件/短信服务规则</p>
		<p class="p_line_height">手拉手保留通过邮件和短信的形式，对本网站及手拉手目录注册、购物用户发送订单信息、促销活动等告知服务的权利。如果您在手拉手注册、购物，表明您已默示接受此项服务。</p>
		<p class="p_line_height">4. 送货规则</p>
		<p class="p_line_height">手拉手将会把商品送到您所指定的送货地址。所有在手拉手网站上列出的送货时间为参考时间，供用户参照使用。参考时间的计算是根据库存状况、正常的处理过程和送货时间、送货地点的基础上估计得出的。</p>
		<p class="p_line_height">5. 退货规则</p>
		<p class="p_line_height">手拉手保留对商品退货、补货的解释权和限制权。在下订单的同时，您也同意了手拉手的《退货规则》。</p>
		<p class="p_line_height">6. 处罚规则</p>
		<p class="p_line_height">① 手拉手有权对用户的注册数据及购买行为进行查阅，发现注册数据或购买行为中存在任何问题或怀疑，均有权向用户发出询问及要求改正的通知或者直接作出删除等处理。</p>
		<p class="p_line_height">②经国家生效法律文书或行政处罚决定确认用户存在违法行为，或者手拉手有足够事实依据可以认定用户存在违法或违反服务协议行为的，手拉手有权在网站上以网络发布形式公布用户的违法行为。</p>
		<p class="p_line_height">③对于用户在手拉手发布的下列各类信息，手拉手有权在不通知用户的前提下进行删除或采取其它限制性措施。包括以炒作信用为目的的信息；存在欺诈等恶意或虚假内容的信息；存在与网上购物无关或不是以购物为目的的信息；存在试图扰乱正常购物秩序因素的信息；该信息违反公共利益或可能严重损害手拉手和其它用户合法利益的。</p>
		<p class="p_line_height">7.变更规则</p>
		<p class="p_line_height">手拉手可以在没有特殊通知的情况下自行变更本规则、任何其它条款和条件、规则或用户资格的任何方面。 对这些条款的任何修改将被包含在手拉手更新的规则中。如果任何变更被认定为无效、废止或因任何原因不可执行，则该变更是可分割的，且不影响其它变更或条件的有效性或可执行性。在变更这些规则后，用户对手拉手的继续使用即构成用户对变更的接受。</p>
		<p class="p_line_height">8. 终止规则</p>
		<p class="p_line_height">手拉手可以不经通知而自行决定终止全部或部分规则，或终止用户的会员资格。即使手拉手没有要求或强制用户严格遵守这些规则，也并不构成对属于手拉手的任何权利的放弃。如果用户在手拉手的客户账户被关闭，那么也将丧失相应的会员资格。对于该用户会员资格的丧失，用户对手拉手不能主张任何权利或为此索赔。</p>
		<p class="p_line_height">9. 责任限制</p>
		<p class="p_line_height">除了手拉手的使用规则中规定的其它限制和除外情况之外，在中国法律法规所允许的限度内，对于因在手拉手购物引起的或与之有关的任何直接的、间接的、特殊的、附带的、后果性的或惩罚性的损害，或任何其它性质的损害，手拉手、手拉手的董事、管理人员、雇员、代理或其它代表在任何情况下都不承担责任。手拉手的全部责任 ，不论是合同、保证、侵权（包括过失）项下的还是任何其它的责任，均不超过用户所购买的与该索赔有关的商品价值额。这些责任排除和限制条款将在法律所允许的最大限度内适用，并在用户资格被撤销或终止后仍继续有效。</p>
		
		<div class="content_title">六、 服务的中断和终止</div>
		<p class="p_line_height">1. 如用户向手拉手提出注销注册用户身份时，经手拉手审核同意，由手拉手注销该注册用户，用户即解除与手拉手的服务协议关系。但注销该用户账号后，手拉手仍保留下列权利：</p>
		<p class="p_line_height">① 用户注销后，手拉手有权保留该用户的注册数据及以前的交易行为记录；</p>
		<p class="p_line_height">② 用户注销后，如用户在注销前在手拉手交易平台上存在违法行为或违反合同的行为，手拉手仍可行使本服务协议所规定的权利。</p>
		<p class="p_line_height">2. 在下列情况下，手拉手可以通过注销用户的方式终止服务：</p>
		<p class="p_line_height">① 在用户违反本服务协议相关规定时，手拉手有权终止向该用户提供服务。手拉手将在中断服务时通知用户。但如该用户在手拉手终止提供服务后，再一次直接或间接或以他人名义注册为手拉手用户的，手拉手有权再次单方面终止向该用户提供服务；</p>
		<p class="p_line_height">② 如手拉手通过用户提供的信息与用户联系时，发现用户在注册时填写的电子邮箱已不存在或无法接收电子邮件的，经手拉手以其它联系方式通知用户更改，而用户在三个工作日内仍未能提供新的电子邮箱地址的，手拉手有权终止向该用户提供服务；</p>
		<p class="p_line_height">③ 一经手拉手发现用户注册数据中主要内容是虚假的，手拉手有权随时终止向该用户提供服务；</p>
		<p class="p_line_height">④ 本服务协议终止或更新时，用户明示不愿接受新的服务协议的；</p>
		<p class="p_line_height">⑤ 其它手拉手认为需终止服务的情况。</p>
		
		<div class="content_title">七、 适用的法律和管辖权</div>
		<p class="p_line_height">用户和手拉手之间的契约将适用中华人民共和国的法律，所有的争端将诉诸于手拉手所在地的人民法院。</p>
		
		<div class="content_title">八、 版权</div>
		<p class="p_line_height">1. 手拉手网站上的图表、标识、网页页眉、按钮图标、文字、服务品名等标示在网站上的信息都是九城天时生态农业有限公司的财产，受中国和国际知识产权法的保护。未经手拉手许可，用户不得以任何可能引起消费者混淆的方式或任何贬低或诽谤北京九城集团的方式用于与非手拉手的任何商品或服务上。在手拉手网站上出现的不属于上述主体的所有其他商标是其商标权利人各自的财产，这些权利人可能是也可能不是与九城集团相关联、相联系或由北京九城集团的关联公司赞助的。未经九城集团或相关商标所有人的书面许可，手拉手网站上的任何东西都不应被解释为以默许或其他方式授予许可或使用网站上出现的商标的权利。</p>
		<p class="p_line_height">2. 手拉手用户发表的文章仅代表作者本人观点，与手拉手立场无关。作者文责自负。</p>
		<p class="p_line_height">3. 手拉手有权将在本网站发表的商品使用体验、商品讨论或图片自行使用或者与其他人合作使用于其他用途，包括但不限于网站、电子杂志、杂志、刊物等，使用时需为作者署名，以发表文章时注明的署名为准。文章有附带版权声明者除外。</p>
		<p class="p_line_height">4. 用户在手拉手网站上发表的文章及图片（包括转贴的文章及图片）版权仅归原作者所有，若作者有版权声明或原作从其它网站转载而附带有原版权声明者，其版权归属以附带声明为准。任何转载、引用发表于手拉手的版权文章须符合以下规范：</p>
		<p class="p_line_height">① 用于非商业、非盈利、非广告性目的时需注明作者及文章及图片的出处为"手拉手"或“www.365020.com”。</p>
		<p class="p_line_height">② 用于商业、盈利、广告性目的时需征得文章或图片原作者的同意，并注明作者姓名、授权范围及原作出处"手拉手"或“www.365020.com”。</p>
		<p class="p_line_height">③ 任何文章或图片的修改或删除均应保持作者原意并征求原作者同意，并注明授权范围。</p>
		
		<div class="content_title">九、 不承诺担保和责任限制</div>
		<p class="p_line_height">1.除非另有明确的书面说明，手拉手网站、目录及其所包含的或以其它方式通过本网站提供给用户的全部信息、内容、材料、商品（包括软件）和服务，是由北京九城天时生态农业有限公司在"按现状"和"按现有"的基础上提供的。</p>
		<p class="p_line_height">2. 除非另有明确的书面说明, 北京九城集团不对本网站的运营及其包含在本网站上的信息、内容、材料、商品(包括软件)或服务作任何形式的、明示或默示的声明或担保（除根据中华人民共和国法律规定的以外）。用户明确同意自担风险使用本网站。</p>
		<p class="p_line_height">3. 在适用法律所允许的最大限度内 ，北京九城集团及其关联公司不承诺所有明示或默示的担保，包括但不限于对适销性和满足特定目的的默示担保。北京九城集团及其关联公司不担保手拉手网站、其所包含的或以其它方式通过本网站提供给您的全部信息、内容、材料、商品（包括软件）和服务、其服务器或从手拉手网站发出的电子信件没有病毒或其他有害成分。除非另有明确的书面说明，北京九城集团不对由于使用手拉手网站、或由于其所包含的或以其它方式通过本网站提供给用户的全部信息、内容、材料、商品（包括软件）和服务、或购买和使用商品引起的任何损害承担责任（除非根据中华人民共和国法律应承担责任的以外），包括但不限于直接、间接或附带的惩罚性和结果性的损害赔偿。</p>
		
		<div class="content_title">十、 隐私</div>
		<p class="p_line_height">1. 在用户加入手拉手成为会员时，用户根据手拉手要求提供的个人注册信息；在用户使用手拉手服务、参加手拉手活动，或访问手拉手网页时，手拉手自动接收并记录的用户浏览器上的服务器数值，包括但不限于IP地址等数据及用户要求取用的网页记录。</p>
		<p class="p_line_height">2. 手拉手收集到的用户在手拉手购物等的有关数据，包括但不限于购物、上传图片、发布商品使用体验、参与商品讨论、邀请关注好友和留言等数据。</p>
		<p class="p_line_height">3. 手拉手通过合法途径从商业伙伴处取得的用户个人数据。</p>
		<p class="p_line_height">4. 信息使用：</p>
		<p class="p_line_height">① 手拉手不会向任何人出售或出借用户的个人信息，除非事先得到用户的许可。</p>
		<p class="p_line_height">② 手拉手亦不允许任何第三方以任何手段收集、编辑、出售或者无偿传播用户的个人信息。任何用户如从事上述活动，一经发现，手拉手有权立即终止与该用户的服务协议，查封其账号。</p>
		<p class="p_line_height">5. 为服务用户的目的，手拉手可能通过使用用户的个人信息，向用户提供服务，包括但不限于向用户发出商品和服务信息，或者与手拉手合作伙伴共享信息以便他们向用户发送有关其商品和服务的信息（后者需要用户的事先同意）。</p>
		<p class="p_line_height">6. 信息披露：用户的个人信息将在下述情况下部分或全部被披露：</p>
		<p class="p_line_height">① 经用户同意，向第三方披露；</p>
		<p class="p_line_height">② 如用户是合资格的知识产权投诉人并已提起投诉，应被投诉人要求，向被投诉人披露，以便双方处理可能的权利纠纷；</p>
		<p class="p_line_height">③ 根据法律的有关规定，或者行政或司法机构的要求，向第三方或者行政、司法机构披露；</p>
		<p class="p_line_height">④ 如果用户出现违反中国有关法律或者网站政策的情况，需要向第三方披露；</p>
		<p class="p_line_height">⑤ 为提供用户所要求的商品和服务，而必须和第三方分享用户的个人信息；</p>
		<p class="p_line_height">⑥ 其它手拉手根据法律或者网站政策认为合适的披露。</p>
		<p class="p_line_height">7. 信息安全：</p>
		<p class="p_line_height">① 手拉手账户和提现功能有密码保护功能，请妥善管用户的账户及密码信息；在使用手拉手服务进行网上购物时，请用户妥善保护自己的个人信息，仅在必要的情形下向他人提供；</p>
		<p class="p_line_height">② 如果用户发现自己的个人信息泄密，尤其是手拉手账户及密码或提现密码发生泄露，请用户立即联络手拉手客服，以便手拉手采取相应措施。</p>
		<p class="p_line_height">8. Cookie的使用：</p>
		<p class="p_line_height">① 通过手拉手所设Cookie所取得的有关信息，将适用本政策；</p>
		<p class="p_line_height">② 在手拉手上发布广告的公司通过广告在用户计算机上设定的Cookies，将按其自己的隐私权政策使用；</p>
		<p class="p_line_height">③ 编辑和删除个人信息的权限： 用户可以点击我的手拉手对用户的个人信息进行编辑和删除，除非手拉手另有规定。</p>
		
	</div>
	
	<%@include file="../common/foot.jsp" %>
</body>
</html>
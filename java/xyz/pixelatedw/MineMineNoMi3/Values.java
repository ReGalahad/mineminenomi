package xyz.pixelatedw.MineMineNoMi3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class Values 
{
	public static List<Item> abilities = new ArrayList();
	public static List<Item> devilfruits = new ArrayList();
	public static List<Item> logias = new ArrayList();
	public static List<Item> miscItems = new ArrayList();
	public static List<Block> miscBlocks = new ArrayList();
	public static List<Object[]> customDFs = new ArrayList();
	
	public static final int MAX_DORIKI = 10000;
	public static final int MAX_ULTRACOLA = 10;
	public static final int MAX_GENERAL = 999999999; //Used by Bounty, Reputation, Belly & Extol
	public static final int MAX_CREW = 50;
	
	public static final Item[] KAIROSEKI_ITEMS = new Item[] {ListMisc.Kairoseki, ListMisc.KairosekiBullets, ListMisc.DenseKairoseki};
	
	public static HashMap<String, String> abilityDesc = new HashMap<String, String>();
	
	static
	{
		abilityDesc.put("hiken", "Turns the user\\'s fist into flames and launches it towards the target.");
		abilityDesc.put("higan", "Turns the user\\'s fingertips into flames and shoots bullets made of fire from them.");
		abilityDesc.put("hidaruma", "Creates small green fireballs that set the target on fire.");
		abilityDesc.put("jujika", "Launches a cross-shaped column of fire at the opponent, leaving a cross of fire.");
		abilityDesc.put("enjomo", "Creates a circle of fire around the user, burning everyone inside of it.");
		abilityDesc.put("daienkaientei", "Amasses the user\\'s flames into a gigantic fireball that the user hurls at the opponent.");
		
		abilityDesc.put("iceblockpartisan", "Creates several spears of ice that the user hurls at the enemy.");
		abilityDesc.put("iceball", "Creates a sphere of ice that surrounds the opponent.");
		abilityDesc.put("iceage", "Freezes a large area around the user and everyone inside of it.");
		abilityDesc.put("icetimecapsule", "A wave of ice is sent along the ground and freezes every enemy it hits.");
		abilityDesc.put("iceblockpheasant", "Releases a massive wave of ice in the shape of a pheasant.");
		
		abilityDesc.put("springhopper", "Turning the userps legs into springs, which launches them into the air.");
		abilityDesc.put("springdeathknock", "By turning the user's arm into a spring and compressing it, they can launch a powerful punch.");
		abilityDesc.put("springsnipe", "Turning the user's forelegs into springs, they can launch themselves directly at the opponent.");
		
		abilityDesc.put("yasakaninomagatama", "Fires a torrent of deadly light particles, causing huge destruction.");
		abilityDesc.put("yatanokagami", "Uses light to instantly teleport the user to their desired location.");
		abilityDesc.put("amaterasu", "Creates an immense beam of light, which causes massive damage.");
	
		abilityDesc.put("shishanote", "Shoots invisible projectiles that explode upon impact.");
		abilityDesc.put("skatting", "Turns the user\\'s entire body invisible.");

		abilityDesc.put("noronorobeam", "Shoots a beam of photons at the opponent, completely slowing them down.");

		abilityDesc.put("gomugomunopistol", "The user stretches their arm to hit the opponent.");
		abilityDesc.put("gomugomunobazooka", "The user stretches their arms to send the opponent flying by hitting them with both palms");
		abilityDesc.put("gearsecond", "By sppeding up their blood flow, the user gains strength, speed and mobility.");
		abilityDesc.put("gearthird", "By blowing air and inflating their body, the user\\'s attacks get bigger and gain incredible strength.");
		abilityDesc.put("gearforth", "The user inflates their muscle structure to tremendously increase the power of their attacks.");
		
		abilityDesc.put("powerpoint", "The user transforms into a half-bison hybrid, which focuses on strength, Allows the user to use \\'Kokutei Cross\\' and \\'Fiddle Banff\\'");
		abilityDesc.put("kokuteicross", "The transformed user crosses their hooves to hit the opponent.");
		abilityDesc.put("fiddlebanff", "The transformed user dashes towards the opponent to crash against them with great power.");
		
		abilityDesc.put("room", "Creates a spherical space around the user, in which they can manipulate anything with other skills.");
		abilityDesc.put("countershock", "Releases a strong electrical current, which shocks the opponent.");
		abilityDesc.put("mes", "Removes the heart of the user\\'s target, which they can then damage to hurt the opponent.");
		abilityDesc.put("gammaknife", "Creates a blade of gamma radiation, which massively damages the opponent\\'s organs");
		
		abilityDesc.put("elthor", "Focuses the user\\'s electricity to strike the opponent with lightning from above.");
		abilityDesc.put("voltvari", "Creates a concentrated ball of lightning, which varies in power.");
		abilityDesc.put("raigo", "Creates a huge cloud filled with electricity, which causes massive damage.");
		abilityDesc.put("kari", "Creates an electrical current around the user, which then explodes.");
		abilityDesc.put("sango", "Launches a huge concentrated chunk of electricity at the opponent.");		
				
		abilityDesc.put("kickbomb", "The user kicks their opponent, detonating their leg on impact.");
		abilityDesc.put("nosefancycannon", "Shoots dried mucus at the opponent, which explodes on impact.");

		abilityDesc.put("whiteout", "Shoots clouds of smoke to engulf the opponent and trap them.");
		abilityDesc.put("whitesnake", "Launches a long dense smoke cloud in the shape of a snake to grab the opponent and damage them.");
		abilityDesc.put("whitelauncher", "Transforms the user into smoke and launches them forward.");
		
		abilityDesc.put("darkmatter", "Launches a ball of darkness that engulfs the opponent.");
		abilityDesc.put("kurouzu", "Creates a strong gravitational force, that pulls the opponent towards the user.");
		abilityDesc.put("blackhole", "The user spreads darkness over the target area, which engulfs anyone and anything inside of it.");
		abilityDesc.put("liberation", "The user expells everything sucked up by the \"Black Hole\" at the target location.");
		
		abilityDesc.put("todoroki", "The user shouts and creates a powerful sound blast.");
		
		abilityDesc.put("negativehollow", "The user launches a ghost that drains the target\\'s will.");
		abilityDesc.put("minihollow", "Launches small ghosts at the opponent, exploding upon impact.");
		abilityDesc.put("tokuhollow", "Creates a huge ghost that causes a massive explosion upon impact.");
		
		abilityDesc.put("barrier", "The user creates an impenetrable wall that shields them from attacks.");
		abilityDesc.put("barrierball", "The user creates a spherical barrier around them, shielding them from attacks.");
		abilityDesc.put("barriercrash", "Launches a barrier towards the opponent, smashing it against them.");
		
		abilityDesc.put("hydra", "Launches a dragon made out of liqiud poison at the opponent.");
		abilityDesc.put("chloroball", "The user spits a bubble made of poison towards the enemy, which leaves poison on the ground.");
		abilityDesc.put("dokufugu", "Shoots multiple poisonous bullets at the opponent.");
		
		abilityDesc.put("candlewall", "Creates a wax wall to protect the user.");
		abilityDesc.put("candlehouse", "Creates a big house-like structure made of wax, to protect the user.");
		abilityDesc.put("dorudoruartsmori", "The user fires a harpoon made of wax at the opponent.");
		
		abilityDesc.put("daifunka", "The user covers their fist in lava and fires it at the opponent.");
		abilityDesc.put("meigo", "The user transforms their arm into magma and thrusts it at the opponent.");
		abilityDesc.put("ryuseikazan", "Functions like \"Dai Funka\", but multiple fists are launched at the opponent.");
		abilityDesc.put("bakuretsukazan", "By spreading magma to the surroundings, the user turns everything into lava.");

		abilityDesc.put("brickbat", "Launches bats created from the user\\'s shadow at the the opponent.");
		abilityDesc.put("blackbox", "Encases and suffocates the opponent in a box made of shadows.");
		abilityDesc.put("tsunotokage", "The user creates a lizard-like shadow under his opponent, which pierces them from below.");
		
		abilityDesc.put("kaishin", "The user cracks the air, which causes a small but powerful explosion.");
		abilityDesc.put("kabutowari", "The user punches the air and creates a massive explosion around themselves.");
		abilityDesc.put("shimayurashi", "Launches a powerful explosion which spreads towards the opponent.");
		
		abilityDesc.put("ursusshock", "The user compresses air and sends it towards the opponent to create a massive explosion.");
		abilityDesc.put("padho", "Launches a paw-shaped shockwave at the opponent.");
		abilityDesc.put("tsupparipadho", "Similar to \"Pad Ho\", but fires a barrage of shockwaves.");
		abilityDesc.put("hanpatsu", "Anyone the user punches gets sent flying far into the air.");
		 
		abilityDesc.put("parasite", "The user binds the opponent with a string that renders them immobile.");
		abilityDesc.put("soranomichi", "The user creates strings under their feet to launch themselves into the air.");
		abilityDesc.put("overheat", "The user shoots a rope made of heated strings at the opponent, exploding upon impact.");

		abilityDesc.put("barjan", "Launches a crescent-shaped wave of sand at the opponent, that dehydrates them.");
		abilityDesc.put("grounddeath", "Dries out the surroundings and suffucates all nearby opponents in sand.");
		abilityDesc.put("sables", "The user launches a compressed sandstorm at the opponent, which sends them flying.");		
		abilityDesc.put("desertspada", "The user extends their sand along the ground, splitting it and suffocating everything it its path. ");		
		
		abilityDesc.put("karakuni", "Removes the oxygen around the user, suffocating everyone in the vicinity.");
		abilityDesc.put("gastanet", "The user fills castanets with unstable gas, whcih causes an explosion when slammed together.");
		abilityDesc.put("gastille", "Shoots a beam of poisonous gas from the user\\'s mouth, that explodes on impact.");		
		abilityDesc.put("gasrobe", "Launches a cloud of poison at the opponent.");
		
		abilityDesc.put("fubuki", "The user releases an extremely cold stream of snow that spreads into the air around them.");
		abilityDesc.put("kamakura", "Creates an igloo-like snow barrier around the user.");
		abilityDesc.put("kamakurajussoshi", "Like \"Kamakura\", but creates a multi-layered snow barrier.");		
		abilityDesc.put("yukirabi", "Launches numerous hardened snowballs, that have the shape of a rabbit\\'s head.");
		abilityDesc.put("tabirayuki", "The user creates a sword made of solid hardened snow.");

		abilityDesc.put("soru", "Allows the user to move at an extremely high speed.");
		abilityDesc.put("tekkai", "Hardens the user's body to protect themselves, but they're unable to move.");
		abilityDesc.put("geppo", "The user kicks the air beneath them to launch themselves into the air.");
		abilityDesc.put("rankyaku", "By kicking at a very high speed, the user launches an air blade at the opponent.");
		abilityDesc.put("shigan", "The user thrusts their finger at the opponent, to pierce them.");
		abilityDesc.put("kamie", "Maked the user's body flexible in order to avoid attacks.");
		
		abilityDesc.put("uchimizu", "The user hurls big and fast water droplets at the opponent.");
		abilityDesc.put("soshark", "The user fires densely compressed shark-shaped waterbullet at the opponent.");
		abilityDesc.put("samehadashotei", "The user concentrates their power to their palms, protecting themselves from attacks.");
		abilityDesc.put("karakusagawaraseiken", "The user punches the air, which sends a shockwave through water vapor in the air.");

		abilityDesc.put("freshfire", "The user heats up and breathes fire like a flamethrower at the opponent.");
		abilityDesc.put("colaoverdrive", "The user absorbs all of their cola at once to boost their physical abilities.");
		abilityDesc.put("radicalbeam", "After charging, the user launches a powerful beam of energy at the opponent.");
		abilityDesc.put("strongright", "The user punches the opponent with a metal fist.");
		abilityDesc.put("coupdevent", "Launches a powerful blast of compressed air that blows the opponent away.");

		abilityDesc.put("busoshokuhaki", "The user forms an invisible armor arounf themselves using their willpower, By using this form of Haki, the user can damage Logias.");
		abilityDesc.put("kenbushokuhaki", "Allows the user to sense the presence of others, pointing them to the opponent, Can also locate invisible mobs and players.");
	}
}

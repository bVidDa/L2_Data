/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package quests.Q337_AudienceWithTheLandDragon;

import org.l2jmobius.gameserver.ai.CtrlIntention;
import org.l2jmobius.gameserver.model.actor.Attackable;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.quest.Quest;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.model.quest.State;

public class Q337_AudienceWithTheLandDragon extends Quest
{
	// NPCs
	private static final int GABRIELLE = 30753;
	private static final int ORVEN = 30857; // 1
	private static final int KENDRA = 30851; // 2
	private static final int CHAKIRIS = 30705; // 3
	private static final int KAIENA = 30720; // 4
	private static final int MOKE = 30498; // 1st abyssal
	private static final int HELTON = 30678; // 2nd abyssal
	private static final int GILMORE = 30754; // 3rd abyssal
	private static final int THEODRIC = 30755;
	// Monsters
	private static final int BLOOD_QUEEN = 18001; // 1
	private static final int SACRIFICE_OF_THE_SACRIFICED = 27171; // 1
	private static final int HARIT_LIZARDMAN_SHAMAN = 20644; // 2
	private static final int HARIT_LIZARDMAN_MATRIARCH = 20645; // 2
	private static final int HARIT_LIZARDMAN_ZEALOT = 27172; // 2
	private static final int KRANROT = 20650; // 3
	private static final int HAMRUT = 20649; // 3
	private static final int MARSH_DRAKE = 20680; // 4
	private static final int MARSH_STALKER = 20679; // 4
	private static final int ABYSSAL_JEWEL_1 = 27165; // 1st abyssal
	private static final int JEWEL_GUARDIAN_MARA = 27168;
	private static final int ABYSSAL_JEWEL_2 = 27166; // 2nd abyssal
	private static final int JEWEL_GUARDIAN_MUSFEL = 27169;
	private static final int CAVE_MAIDEN_1 = 20134; // 3rd abyssal
	private static final int CAVE_MAIDEN_2 = 20287;
	private static final int CAVE_KEEPER_1 = 20246;
	private static final int CAVE_KEEPER_2 = 20277;
	private static final int ABYSSAL_JEWEL_3 = 27167;
	private static final int JEWEL_GUARDIAN_PYTON = 27170;
	// Items
	private static final int FEATHER_OF_GABRIELLE = 3852;
	private static final int MARK_OF_WATCHMAN = 3864;
	private static final int REMAINS_OF_SACRIFIED = 3857; // 1
	private static final int TOTEM_OF_LAND_DRAGON = 3858; // 2
	private static final int KRANROT_SKIN = 3855; // 3
	private static final int HAMRUT_LEG = 3856; // 3
	private static final int MARSH_DRAKE_TALONS = 3854; // 4
	private static final int MARSH_STALKER_HORN = 3853; // 4
	private static final int FIRST_FRAGMENT_OF_ABYSS_JEWEL = 3859; // 1st abyssal
	private static final int MARA_FANG = 3862;
	private static final int SECOND_FRAGMENT_OF_ABYSS_JEWEL = 3860; // 2nd abyssal
	private static final int MUSFEL_FANG = 3863;
	private static final int HERALD_OF_SLAYER = 3890;
	private static final int THIRD_FRAGMENT_OF_ABYSS_JEWEL = 3861; // 3rd abyssal
	private static final int PORTAL_STONE = 3865;
	// Variables
	private static boolean _jewel1 = false;
	private static boolean _jewel2 = false;
	private static boolean _jewel3 = false;
	// @formatter:off
	private static final int[][] DROPS_ON_KILL =
	{
		// 0..npcId, 1..cond, 2..cond2, 3..chance, 4..itemId
		{SACRIFICE_OF_THE_SACRIFICED, 1, 1, REMAINS_OF_SACRIFIED},
		{HARIT_LIZARDMAN_ZEALOT, 1, 2, TOTEM_OF_LAND_DRAGON},
		{KRANROT, 1, 3, KRANROT_SKIN},
		{HAMRUT, 1, 3, HAMRUT_LEG},
		{MARSH_DRAKE, 1, 4, MARSH_DRAKE_TALONS},
		{MARSH_STALKER, 1, 4, MARSH_STALKER_HORN},
		{JEWEL_GUARDIAN_MARA, 2, 5, MARA_FANG},
		{JEWEL_GUARDIAN_MUSFEL, 2, 6, MUSFEL_FANG}
	};
	private static final int[][] DROP_ON_ATTACK =
	{
		// 0..npcId, 1..cond, 2..cond2, 3..itemId, 4..amount of mobs, 5..mob
		{ABYSSAL_JEWEL_1, 2, 5, FIRST_FRAGMENT_OF_ABYSS_JEWEL, 20, JEWEL_GUARDIAN_MARA},
		{ABYSSAL_JEWEL_2, 2, 6, SECOND_FRAGMENT_OF_ABYSS_JEWEL, 20, JEWEL_GUARDIAN_MUSFEL},
		{ABYSSAL_JEWEL_3, 4, 7, THIRD_FRAGMENT_OF_ABYSS_JEWEL, 3, JEWEL_GUARDIAN_PYTON},
	};
	// @formatter:on
	
	public Q337_AudienceWithTheLandDragon()
	{
		super(337, "Audience with the Land Dragon");
		
		registerQuestItems(FEATHER_OF_GABRIELLE, MARK_OF_WATCHMAN, REMAINS_OF_SACRIFIED, TOTEM_OF_LAND_DRAGON, KRANROT_SKIN, HAMRUT_LEG, MARSH_DRAKE_TALONS, MARSH_STALKER_HORN, FIRST_FRAGMENT_OF_ABYSS_JEWEL, MARA_FANG, SECOND_FRAGMENT_OF_ABYSS_JEWEL, MUSFEL_FANG, HERALD_OF_SLAYER, THIRD_FRAGMENT_OF_ABYSS_JEWEL);
		
		addStartNpc(GABRIELLE);
		addTalkId(GABRIELLE, ORVEN, KENDRA, CHAKIRIS, KAIENA, MOKE, HELTON, GILMORE, THEODRIC);
		
		addAttackId(ABYSSAL_JEWEL_1, ABYSSAL_JEWEL_2, ABYSSAL_JEWEL_3);
		addKillId(BLOOD_QUEEN, SACRIFICE_OF_THE_SACRIFICED, HARIT_LIZARDMAN_SHAMAN, HARIT_LIZARDMAN_MATRIARCH, HARIT_LIZARDMAN_ZEALOT, KRANROT, HAMRUT, MARSH_DRAKE, MARSH_STALKER, JEWEL_GUARDIAN_MARA, JEWEL_GUARDIAN_MUSFEL, CAVE_MAIDEN_1, CAVE_MAIDEN_2, CAVE_KEEPER_1, CAVE_KEEPER_2, JEWEL_GUARDIAN_PYTON);
	}
	
	@Override
	public String onAdvEvent(String event, Npc npc, Player player)
	{
		String htmltext = event;
		final QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30753-05.htm":
			{
				st.startQuest();
				st.set("drop1", "1");
				st.set("drop2", "1");
				st.set("drop3", "1");
				st.set("drop4", "1");
				st.giveItems(FEATHER_OF_GABRIELLE, 1);
				break;
			}
			case "30753-09.htm":
			{
				if (st.getQuestItemsCount(MARK_OF_WATCHMAN) >= 4)
				{
					st.setCond(2);
					st.set("drop5", "2");
					st.set("drop6", "2");
					st.playSound(QuestState.SOUND_MIDDLE);
					st.takeItems(MARK_OF_WATCHMAN, 4);
				}
				else
				{
					htmltext = null;
				}
				break;
			}
			case "30755-05.htm":
			{
				if (st.hasQuestItems(THIRD_FRAGMENT_OF_ABYSS_JEWEL))
				{
					st.takeItems(THIRD_FRAGMENT_OF_ABYSS_JEWEL, 1);
					st.takeItems(HERALD_OF_SLAYER, 1);
					st.giveItems(PORTAL_STONE, 1);
					st.playSound(QuestState.SOUND_FINISH);
					st.exitQuest(true);
				}
				else
				{
					htmltext = null;
				}
				break;
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(Npc npc, Player player)
	{
		String htmltext = getNoQuestMsg();
		final QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			return htmltext;
		}
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				htmltext = (player.getLevel() < 50) ? "30753-02.htm" : "30753-01.htm";
				break;
			}
			case State.STARTED:
			{
				final int cond = st.getCond();
				switch (npc.getNpcId())
				{
					case GABRIELLE:
					{
						if (cond == 1)
						{
							htmltext = (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 4) ? "30753-06.htm" : "30753-08.htm";
						}
						else if (cond == 2)
						{
							if (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 2)
							{
								htmltext = "30753-10.htm";
							}
							else
							{
								htmltext = "30753-11.htm";
								st.setCond(3);
								st.playSound(QuestState.SOUND_MIDDLE);
								st.takeItems(FEATHER_OF_GABRIELLE, 1);
								st.takeItems(MARK_OF_WATCHMAN, 1);
								st.giveItems(HERALD_OF_SLAYER, 1);
							}
						}
						else if (cond == 3)
						{
							htmltext = "30753-12.htm";
						}
						else if (cond == 4)
						{
							htmltext = "30753-13.htm";
						}
						break;
					}
					case ORVEN:
					{
						if (cond == 1)
						{
							if (st.getInt("drop1") == 1)
							{
								if (st.hasQuestItems(REMAINS_OF_SACRIFIED))
								{
									htmltext = "30857-02.htm";
									st.unset("drop1");
									st.playSound(QuestState.SOUND_MIDDLE);
									st.takeItems(REMAINS_OF_SACRIFIED, 1);
									st.giveItems(MARK_OF_WATCHMAN, 1);
								}
								else
								{
									htmltext = "30857-01.htm";
								}
							}
							else if (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 4)
							{
								htmltext = "30857-03.htm";
							}
							else
							{
								htmltext = "30857-04.htm";
							}
						}
						break;
					}
					case KENDRA:
					{
						if (cond == 1)
						{
							if (st.getInt("drop2") == 1)
							{
								if (st.hasQuestItems(TOTEM_OF_LAND_DRAGON))
								{
									htmltext = "30851-02.htm";
									st.unset("drop2");
									st.playSound(QuestState.SOUND_MIDDLE);
									st.takeItems(TOTEM_OF_LAND_DRAGON, 1);
									st.giveItems(MARK_OF_WATCHMAN, 1);
								}
								else
								{
									htmltext = "30851-01.htm";
								}
							}
							else if (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 4)
							{
								htmltext = "30851-03.htm";
							}
							else
							{
								htmltext = "30851-04.htm";
							}
						}
						break;
					}
					case CHAKIRIS:
					{
						if (cond == 1)
						{
							if (st.getInt("drop3") == 1)
							{
								if (st.hasQuestItems(KRANROT_SKIN, HAMRUT_LEG))
								{
									htmltext = "30705-02.htm";
									st.unset("drop3");
									st.playSound(QuestState.SOUND_MIDDLE);
									st.takeItems(KRANROT_SKIN, 1);
									st.takeItems(HAMRUT_LEG, 1);
									st.giveItems(MARK_OF_WATCHMAN, 1);
								}
								else
								{
									htmltext = "30705-01.htm";
								}
							}
							else if (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 4)
							{
								htmltext = "30705-03.htm";
							}
							else
							{
								htmltext = "30705-04.htm";
							}
						}
						break;
					}
					case KAIENA:
					{
						if (cond == 1)
						{
							if (st.getInt("drop4") == 1)
							{
								if (st.hasQuestItems(MARSH_DRAKE_TALONS, MARSH_STALKER_HORN))
								{
									htmltext = "30720-02.htm";
									st.unset("drop4");
									st.playSound(QuestState.SOUND_MIDDLE);
									st.takeItems(MARSH_DRAKE_TALONS, 1);
									st.takeItems(MARSH_STALKER_HORN, 1);
									st.giveItems(MARK_OF_WATCHMAN, 1);
								}
								else
								{
									htmltext = "30720-01.htm";
								}
							}
							else if (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 4)
							{
								htmltext = "30720-03.htm";
							}
							else
							{
								htmltext = "30720-04.htm";
							}
						}
						break;
					}
					case MOKE:
					{
						if (cond == 2)
						{
							switch (st.getInt("drop5"))
							{
								case 2:
								{
									htmltext = "30498-01.htm";
									st.set("drop5", "1");
									break;
								}
								case 1:
								{
									if (st.hasQuestItems(FIRST_FRAGMENT_OF_ABYSS_JEWEL, MARA_FANG))
									{
										htmltext = "30498-03.htm";
										st.unset("drop5");
										st.playSound(QuestState.SOUND_MIDDLE);
										st.takeItems(FIRST_FRAGMENT_OF_ABYSS_JEWEL, 1);
										st.takeItems(MARA_FANG, 1);
										st.giveItems(MARK_OF_WATCHMAN, 1);
									}
									else
									{
										htmltext = "30498-02.htm";
									}
									break;
								}
								case 0:
								{
									if (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 2)
									{
										htmltext = "30498-04.htm";
									}
									else
									{
										htmltext = "30498-05.htm";
									}
									break;
								}
							}
						}
						break;
					}
					case HELTON:
					{
						if (cond == 2)
						{
							switch (st.getInt("drop6"))
							{
								case 2:
								{
									htmltext = "30678-01.htm";
									st.set("drop6", "1");
									break;
								}
								case 1:
								{
									if (st.hasQuestItems(SECOND_FRAGMENT_OF_ABYSS_JEWEL, MUSFEL_FANG))
									{
										htmltext = "30678-03.htm";
										st.unset("drop6");
										st.playSound(QuestState.SOUND_MIDDLE);
										st.takeItems(SECOND_FRAGMENT_OF_ABYSS_JEWEL, 1);
										st.takeItems(MUSFEL_FANG, 1);
										st.giveItems(MARK_OF_WATCHMAN, 1);
									}
									else
									{
										htmltext = "30678-02.htm";
									}
									break;
								}
								case 0:
								{
									if (st.getQuestItemsCount(MARK_OF_WATCHMAN) < 2)
									{
										htmltext = "30678-04.htm";
									}
									else
									{
										htmltext = "30678-05.htm";
									}
									break;
								}
							}
						}
						break;
					}
					case GILMORE:
					{
						if ((cond == 1) || (cond == 2))
						{
							htmltext = "30754-01.htm";
						}
						else if (cond == 3)
						{
							htmltext = "30754-02.htm";
							st.setCond(4);
							st.set("drop7", "1");
							st.playSound(QuestState.SOUND_MIDDLE);
						}
						else if (cond == 4)
						{
							htmltext = (!st.hasQuestItems(THIRD_FRAGMENT_OF_ABYSS_JEWEL)) ? "30754-04.htm" : "30754-05.htm";
						}
						break;
					}
					case THEODRIC:
					{
						if ((cond == 1) || (cond == 2))
						{
							htmltext = "30755-01.htm";
						}
						else if (cond == 3)
						{
							htmltext = "30755-02.htm";
						}
						else if (cond == 4)
						{
							htmltext = (!st.hasQuestItems(THIRD_FRAGMENT_OF_ABYSS_JEWEL)) ? "30755-03.htm" : "30755-04.htm";
						}
						break;
					}
				}
				break;
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onAttack(Npc npc, Player attacker, int damage, boolean isPet)
	{
		final QuestState st = checkPlayerState(attacker, npc, State.STARTED);
		if (st == null)
		{
			return null;
		}
		
		final int npcId = npc.getNpcId();
		for (int[] npcInfo : DROP_ON_ATTACK)
		{
			if (npcInfo[0] != npcId)
			{
				continue;
			}
			
			if (npcInfo[1] != st.getCond())
			{
				break;
			}
			
			final double percentHp = ((npc.getCurrentHp() + damage) * 100) / npc.getMaxHp();
			
			// reward jewel fragment
			if (percentHp < 33)
			{
				if ((getRandom(100) < 33) && (st.getInt("drop" + npcInfo[2]) == 1))
				{
					final int itemId = npcInfo[3];
					if (!st.hasQuestItems(itemId))
					{
						st.giveItems(itemId, 1);
						st.playSound(QuestState.SOUND_ITEMGET);
					}
				}
			}
			// spawn monsters and register spawned
			else if (percentHp < 66)
			{
				if ((getRandom(100) < 33) && (st.getInt("drop" + npcInfo[2]) == 1))
				{
					boolean spawn;
					if (npcId == ABYSSAL_JEWEL_3)
					{
						spawn = _jewel3;
					}
					else if (npcId == ABYSSAL_JEWEL_2)
					{
						spawn = _jewel2;
					}
					else
					{
						spawn = _jewel1;
					}
					
					if (spawn)
					{
						for (int i = 0; i < npcInfo[4]; i++)
						{
							final Npc mob = addSpawn(npcInfo[5], npc.getX() + getRandom(-150, 150), npc.getY() + getRandom(-150, 150), npc.getZ(), npc.getHeading(), true, 60000);
							mob.setRunning();
							((Attackable) mob).addDamageHate(attacker, 0, 500);
							mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
						}
						
						if (npcId == ABYSSAL_JEWEL_3)
						{
							_jewel3 = false;
						}
						else if (npcId == ABYSSAL_JEWEL_2)
						{
							_jewel2 = false;
						}
						else
						{
							_jewel1 = false;
						}
					}
				}
			}
			// reset spawned if npc regenerated to 90% HP and more
			else if (percentHp > 90)
			{
				if (npcId == ABYSSAL_JEWEL_3)
				{
					_jewel3 = true;
				}
				else if (npcId == ABYSSAL_JEWEL_2)
				{
					_jewel2 = true;
				}
				else
				{
					_jewel1 = true;
				}
			}
			break;
		}
		
		return null;
	}
	
	@Override
	public String onKill(Npc npc, Player player, boolean isPet)
	{
		final QuestState st = checkPlayerState(player, npc, State.STARTED);
		if (st == null)
		{
			return null;
		}
		
		final int cond = st.getCond();
		final int npcId = npc.getNpcId();
		switch (npcId)
		{
			case SACRIFICE_OF_THE_SACRIFICED: // Orven's request
			case HARIT_LIZARDMAN_ZEALOT: // Kendra's request
			case KRANROT:// Chakiris's request
			case HAMRUT:
			case MARSH_DRAKE:// Kaiena's request
			case MARSH_STALKER:
			case JEWEL_GUARDIAN_MARA:// Moke's request
			case JEWEL_GUARDIAN_MUSFEL:// Helton's request
			{
				for (int[] npcInfo : DROPS_ON_KILL)
				{
					if (npcInfo[0] != npcId)
					{
						continue;
					}
					
					if ((npcInfo[1] == cond) && (st.getInt("drop" + npcInfo[2]) == 1))
					{
						final int itemId = npcInfo[3];
						if (!st.hasQuestItems(itemId))
						{
							st.giveItems(itemId, 1);
							st.playSound(QuestState.SOUND_ITEMGET);
						}
					}
					break;
				}
				break;
			}
			case BLOOD_QUEEN:// Orven's request
			{
				if ((cond == 1) && (st.getInt("drop1") == 1) && !st.hasQuestItems(REMAINS_OF_SACRIFIED))
				{
					for (int i = 0; i < 8; i++)
					{
						addSpawn(SACRIFICE_OF_THE_SACRIFICED, npc.getX() + getRandom(-100, 100), npc.getY() + getRandom(-100, 100), npc.getZ(), npc.getHeading(), true, 60000);
					}
				}
				break;
			}
			case HARIT_LIZARDMAN_SHAMAN:// Kendra's request
			case HARIT_LIZARDMAN_MATRIARCH:
			{
				if ((cond == 1) && (getRandom(5) == 0) && (st.getInt("drop2") == 1) && !st.hasQuestItems(TOTEM_OF_LAND_DRAGON))
				{
					for (int i = 0; i < 3; i++)
					{
						addSpawn(HARIT_LIZARDMAN_ZEALOT, npc.getX() + getRandom(-50, 50), npc.getY() + getRandom(-50, 50), npc.getZ(), npc.getHeading(), true, 60000);
					}
				}
				break;
			}
			case CAVE_MAIDEN_1:// Gilmore's request
			case CAVE_MAIDEN_2:
			case CAVE_KEEPER_1:
			case CAVE_KEEPER_2:
			{
				if ((cond == 4) && (getRandom(5) == 0) && !st.hasQuestItems(THIRD_FRAGMENT_OF_ABYSS_JEWEL))
				{
					addSpawn(ABYSSAL_JEWEL_3, npc.getX() + getRandom(-50, 50), npc.getY() + getRandom(-50, 50), npc.getZ(), npc.getHeading(), true, 60000);
				}
				break;
			}
		}
		
		return null;
	}
}
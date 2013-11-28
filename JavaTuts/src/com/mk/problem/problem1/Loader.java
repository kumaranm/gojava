package com.mk.problem.problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Loader
{
	public void loadNumeralMappingFile(String pathname)
	{
		try (BufferedReader br = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(pathname))))
		{
			String input = br.readLine();
			while (input != null)
			{
				if (input.startsWith("#"))
				{
					input = br.readLine();
					continue;
				}
				String[] split = input.trim().split("-");
				if (split != null && split.length == 2)
				{
					CodeMapper.add(split[0], split[1]);
				}
				else
				{
					throw new RuntimeException("Invalid mapping record in mapping file");
				}
				input = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void loadCreditMappingFile(String pathname)
	{
		try (Scanner br = new Scanner(new InputStreamReader(Loader.class.getResourceAsStream(pathname))))
		{
			String input = br.nextLine();
			while (input != null)
			{
				if (input.startsWith("#"))
				{
					if (br.hasNext())
					{
						input = br.nextLine();
						continue;
					}
					else
					{
						break;
					}
				}
				String[] split = input.trim().split(" ");
				if (split != null && split.length > 4 && split[split.length - 1].equals("Credits"))
				{
					Interpretor.createExpression(split);
				}
				else
				{
					throw new RuntimeException("Invalid mapping record in mapping file");
				}
				if (br.hasNext())
				{
					input = br.nextLine();
				}
				else
				{
					break;
				}
			}
		}
	}

	public void loadQuestionsFile(String pathname)
	{
		try (Scanner br = new Scanner(new InputStreamReader(Loader.class.getResourceAsStream(pathname))))
		{
			String input = br.nextLine();
			while (input != null)
			{
				if (input.startsWith("#"))
				{
					if (br.hasNext())
					{
						input = br.nextLine();
						continue;
					}
					else
					{
						break;
					}
				}
				String[] split = input.trim().split(" ");
				if (split != null && split.length > 3 && split[split.length - 1].equals("?"))
				{
					if (input.startsWith("how much is"))
					{
						StringBuilder sb = new StringBuilder();
						StringBuilder q = new StringBuilder();
						for (int i = 3; i < split.length - 1; i++)
						{
							q.append(split[i]).append(" ");
							sb.append(CodeMapper.getMap().get(split[i]).name());
						}
						System.out.println(q + "is " + Interpretor.convertRomanToLong(sb));
					}
					else if (input.startsWith("how many Credits is"))
					{
						StringBuilder sb = new StringBuilder();
						StringBuilder q = new StringBuilder();
						boolean allowCode = true;
						boolean allowMetal = true;
						Metal a = null;
						for (int i = 4; i < split.length - 1; i++)
						{
							q.append(split[i]).append(" ");
							if (allowCode && CodeMapper.getMap().containsKey(split[i]))
							{
								sb.append(CodeMapper.getMap().get(split[i]).name());
							}
							else if (allowMetal && Metal.valueOf(split[i].toUpperCase()) != null)
							{
								allowCode = false;
								a = Metal.valueOf(split[i].toUpperCase());
								allowMetal = false;
								break;
							}
						}

						Long romanValue = Interpretor.convertRomanToLong(sb);
						double cost = romanValue * MetalCreditMapper.get(a);
						System.out.println(q + "is " + cost / 100 + " Credits");
					}
					else
					{
						System.out.println("I have no idea what you are talking about");
					}
				}
				else
				{
					System.out.println("I have no idea what you are talking about");
				}
				if (br.hasNext())
				{
					input = br.nextLine();
				}
				else
				{
					break;
				}
			}
		}
	}
}

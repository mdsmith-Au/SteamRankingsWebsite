package com.steamrankings.website.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.leaderboards.Leaderboards;
import com.steamrankings.service.api.leaderboards.RankEntryByAchievements;
import com.steamrankings.website.Application;

@Controller
public class LeaderboardController {

    
    @ModelAttribute("rankentries")
    public List<RankEntryByAchievements> populateTable() {
        RankEntryByAchievements r1 = new RankEntryByAchievements(1, 1234, "swag",
        500, "50", 50, "Canada"); RankEntryByAchievements r2 = new
        RankEntryByAchievements(2, 4321, "yolo", 501, "51", 100, "USA");
        List<RankEntryByAchievements> curList = new
        ArrayList<RankEntryByAchievements>(); curList.add(r1); curList.add(r2);

        return curList;
    }
    

    @RequestMapping("/leaderboard")
    public String getLeaderboard(String type, Model model) {
    	if (type == null || type.equals("achievements")) {
            System.out.println(type);
    		try {
                byAchievements(model);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (APIException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "leaderboard";
    	} else if (type.equals("playtime")) {
    		try {
                byPlayTime(model);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (APIException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		return "leaderboard";
    	}else if (type.equals("completionrate")) {
    		try {
                byCompletionRate(model);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (APIException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		return "leaderboard";
    	} else {
            return "redirect:/?error=" + "this is a bad link";    		
    	}
    }
    
	private void byCompletionRate(Model model) throws ClientProtocolException, APIException, IOException {
	    List<RankEntryByAchievements> rankEntries = Leaderboards.getRanksByCompletionRate(1, 10, Application.client);

        if (rankEntries != null) {
            for (int i = 0; i < rankEntries.size(); i++) {
                if (rankEntries.get(i).getCountryCode() != null && !rankEntries.get(i).getCountryCode().equals("")) {
                    String countryFlag = rankEntries.get(i).getCountryCode().toLowerCase();
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime() ,  countryFlag));
                } else {
                    String countryFlag = "un";
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                }
            }
            model.addAttribute("rankentries", rankEntries);
        }
    }


	private void byPlayTime(Model model) throws ClientProtocolException, APIException, IOException {
    	List<RankEntryByAchievements> rankEntries = Leaderboards.getRanksByTotalPlayTime(1, 10, Application.client);
 
        if (rankEntries != null) {
            for (int i = 0; i < rankEntries.size(); i++) {
                if (rankEntries.get(i).getCountryCode() != null && !rankEntries.get(i).getCountryCode().equals("")) {
                    String countryFlag = rankEntries.get(i).getCountryCode().toLowerCase();
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime() ,  countryFlag));
                } else {
                    String countryFlag = "un";
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                }
            }
            model.addAttribute("rankentries", rankEntries);
        }
    }
    
    private void byAchievements(Model model) throws ClientProtocolException, APIException, IOException {
        List<RankEntryByAchievements> rankEntries = Leaderboards.getRanksByAchievementTotal(1, 10, Application.client);

        if (rankEntries != null) {
            for (int i = 0; i < rankEntries.size(); i++) {
                if (rankEntries.get(i).getCountryCode() != null && !rankEntries.get(i).getCountryCode().equals("")) {
                    String countryFlag = rankEntries.get(i).getCountryCode().toLowerCase();
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                } else {
                    String countryFlag = "un";
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                }
            }
            model.addAttribute("rankentries", rankEntries);
        }   	
    }
}
